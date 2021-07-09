package net.swordie.ms.connection.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.swordie.ms.client.Account;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Login;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.handlers.AdminHandler;
import net.swordie.ms.handlers.ChatHandler;
import net.swordie.ms.handlers.LoginHandler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.world.World;
import net.swordie.ms.world.WorldHandler;
import net.swordie.ms.world.shop.cashshop.CashShopHandler;
import org.apache.log4j.LogManager;

import java.io.IOException;

import static net.swordie.ms.connection.netty.NettyClient.CLIENT_KEY;


/**
 * Created by Tim on 2/28/2017.
 */
public class ChannelHandler extends SimpleChannelInboundHandler<InPacket> {

    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        NettyClient o = ctx.channel().attr(CLIENT_KEY).get();
//        if(!LoginAcceptor.channelPool.containsKey(o.getIP())) {
//            System.out.println("[Dropping currently unknown client]");
//            o.close();
//        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.debug("[ChannelHandler] | Channel inactive.");
        Client c = (Client) ctx.channel().attr(CLIENT_KEY).get();
        Account acc = c.getAccount();
        Char chr = c.getChr();
        if(c != null && chr != null && !chr.isChangingChannel()) {
            chr.logout();
        } else if (c != null && chr != null && chr.isChangingChannel()) {
            chr.setChangingChannel(false);
        } else if (acc != null) {
            acc.unstuck();
        } else {
            log.warn("[ChannelHandler] | Was not able to save character, data inconsistency may have occurred.");
        }
        NettyClient o = ctx.channel().attr(CLIENT_KEY).get();
        if (o != null) {
            o.close();
        }
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, InPacket inPacket) {
        Client c = (Client) ctx.channel().attr(CLIENT_KEY).get();
        Char chr = c.getChr();
        int op = inPacket.decodeShort();
        if (c.mEncryptedOpcode.containsKey(op)) {
            op = c.mEncryptedOpcode.get(op);
        }
        InHeader opHeader = InHeader.getInHeaderByOp(op);
        if(opHeader == null) {
            handleUnknown(inPacket, op);
            return;
        }
        if(!InHeader.isSpamHeader(InHeader.getInHeaderByOp(op))) {
            log.debug(String.format("[In]\t| %s, %d/0x%s\t| %s", InHeader.getInHeaderByOp(op), op, Integer.toHexString(op).toUpperCase(), inPacket));
        }
        switch(opHeader) {
            case CONNECT_CHAT:
                ChatHandler.handleConnect(c, inPacket);
                break;
            case FRIEND_CHAT:
                ChatHandler.handleFriendChat(c, inPacket);
                break;
            case GUILD_CHAT:
                ChatHandler.handleGuildChat(c, inPacket);
                break;
            case LOGIN_BASIC_INFO:
                break;
            case PERMISSION_REQUEST:
                LoginHandler.handlePermissionRequest(c, inPacket);
                break;
            case USE_AUTH_SERVER:
                LoginHandler.handleAuthServer(c, inPacket);
                break;
            case USER_DAMAGE_SKIN_SAVE_REQUEST:
                WorldHandler.handleUserDamageSkinSaveRequest(chr, inPacket);
                break;
            case CLIENT_START:
                LoginHandler.handleClientStart(c, inPacket);
                break;
            case WVS_CRASH_CALLBACK:
                if (c != null && c.getChr() != null) {
                    c.getChr().setChangingChannel(false);
                    c.getChr().logout();
                }
                break;
            case EXCEPTION_LOG:
                LoginHandler.handleExceptionLog(c, inPacket);
                break;
            case USER_ACTIVATE_NICK_ITEM:
                WorldHandler.handleUserActiveNickItem(c, inPacket);
                break;
            case USER_REQUEST_CHANGE_MOB_ZONE_STATE:
                WorldHandler.handleUserRequestChangeMobZoneState(c, inPacket);
                break;
            case USER_TRUNK_REQUEST:
                WorldHandler.handleUserTrunkRequest(c, inPacket);
                break;
            case USER_PET_FOOD_ITEM_USE_REQUEST:
                WorldHandler.handleUserPetFoodItemUseRequest(c, inPacket);
                break;
            case USER_REGISTER_PET_AUTO_BUFF_REQUEST:
                WorldHandler.handleUserRegisterPetAutoBuffRequest(chr, inPacket);
                break;
            case USER_ITEM_SKILL_SOCKET_UPGRADE_ITEM_USE_REQUEST:
                WorldHandler.handleUserItemSkillSocketUpdateItemUseRequest(c, inPacket);
                break;
            case USER_ITEM_SKILL_OPTION_UPGRADE_ITEM_USE_REQUEST:
                WorldHandler.handleUserItemSkillOptionUpdateItemUseRequest(c, inPacket);
                break;
            case FRIEND_REQUEST:
                WorldHandler.handleFriendRequest(c, inPacket);
                break;
            case USER_GATHER_ITEM_REQUEST:
                WorldHandler.handleUserGatherItemRequest(c, inPacket);
                break;
            case USER_SORT_ITEM_REQUEST:
                WorldHandler.handleUserSortItemRequest(c, inPacket);
                break;
            case USER_SCRIPT_ITEM_USE_REQUEST:
                WorldHandler.handleUserScriptItemUseRequest(c, inPacket);
                break;
            case USER_STAT_CHANGE_ITEM_USE_REQUEST:
                WorldHandler.handleUserStatChangeItemUseRequest(c, inPacket);
                break;
            case USER_STAT_CHANGE_ITEM_CANCEL_REQUEST:
                WorldHandler.handleUserStatChangeItemCancelRequest(chr, inPacket);
                break;
            case USER_MACRO_SYS_DATA_MODIFIED:
                WorldHandler.handleUserMacroSysDataModified(c, inPacket);
                break;
            case USER_ANTI_MACRO_QUESTION_RESULT:
                WorldHandler.handleUserAntiMacroQuestionResult(c, inPacket);
                break;
            case USER_ANTI_MACRO_REFRESH_REQUEST:
                WorldHandler.handleUserAntiMacroRefreshResult(c, inPacket);
                break;
            case USER_ACTIVATE_DAMAGE_SKIN:
                WorldHandler.handleUserActivateDamageSkin(c, inPacket);
                break;
            case USER_ACTIVATE_PET_REQUEST:
                WorldHandler.handleUserActivatePetRequest(c, inPacket);
                break;
            case USER_ACTIVATE_EFFECT_ITEM:
                WorldHandler.handleUserActivateEffectItem(c, inPacket);
                break;
            case USER_DEFAULT_WING_ITEM:
                WorldHandler.handleUserDefaultWingItem(chr, inPacket);
                break;
            case USER_SET_DRESS_CHANGED_REQUEST:
                WorldHandler.handleUserSetDressChangedRequest(chr, inPacket);
                break;
            case EVENT_UI_REQ:
                WorldHandler.handleEventUiReq(c, inPacket);
                break;
            case USER_ACTIVATE_DAMAGE_SKIN__PREMIUM:
                WorldHandler.handleUserActivateDamageSkinPremium(c, inPacket);
                break;
            case FISHING_INFO:
                break;
            case PARTY_INVITABLE_SET:
                WorldHandler.handlePartyInvitableSet(c, inPacket);
                break;
            case USER_SKILL_USE_REQUEST:
                WorldHandler.handleUserSkillUseRequest(c, inPacket);
                break;
            case USER_SKILL_CANCEL_REQUEST:
                WorldHandler.handleTemporaryStatResetRequest(c, inPacket);
                break;
            case USER_SKILL_PREPARE_REQUEST:
                WorldHandler.handleUserSkillPrepareRequest(chr, inPacket);
                break;
            case PONG:
                LoginHandler.handlePong(c, inPacket);
                break;
            case CHECK_LOGIN_AUTH_INFO:
                LoginHandler.handleCheckLoginAuthInfo(c, inPacket);
                break;
            case SERVERSTATUS_REQUEST:
                LoginHandler.handleServerStatusRequest(c, inPacket);
                break;
            case WORLD_STATUS_REQUEST:
                LoginHandler.handleWorldStatusRequest(c, inPacket);
                break;
            case WORLD_INFO_REQUEST:
            case WORLD_LIST_REQUEST:
            case LOGOUT_WORLD:
            case WORLD_LIST_REQ:
                LoginHandler.handleWorldListRequest(c, inPacket);
                break;
            case SELECT_WORLD_BUTTON:
                LoginHandler.handleSelectWorldButton(c, inPacket);
                break;
            case SELECT_WORLD:
                LoginHandler.handleSelectWorld(c, inPacket);
                break;
            case CHECK_SPW_REQUEST:
                LoginHandler.handleCheckSpwRequest(c, inPacket, false);
                break;
            case CHECK_DUPLICATE_ID:
                LoginHandler.handleCheckDuplicatedID(c, inPacket);
                break;
            case CREATE_NEW_CHARACTER:
                LoginHandler.handleCreateNewCharacter(c, inPacket);
                break;
            case DELETE_CHARACTER:
                LoginHandler.handleDeleteCharacter(c, inPacket);
                break;
            case PRIVATE_SERVER_PACKET:
                LoginHandler.handlePrivateServerPacket(c, inPacket);
                break;
            case CLIENT_ERROR:
                LoginHandler.handleClientError(c, inPacket);
                break;
            case CHAR_SELECT_NO_PIC:
                LoginHandler.handleCharSelectNoPic(c, inPacket);
                break;
            case CHAR_SELECT:
                LoginHandler.handleCharSelect(c, inPacket);
                break;
            case UPDATE_CLIENT_ENVIRONMENT:
            case WVS_SET_UP_STEP:
            case SECURITY_PACKET:
            case USER_CALC_DAMAGE_STAT_SET_REQUEST:
                break;
            case USER_MIGRATE_TO_CASH_SHOP_REQUEST:
                WorldHandler.handleUserMigrateToCashShopRequest(c, inPacket);
            case AUTH_HEARTBEAT:
                break;
            case MIGRATE_IN:
                WorldHandler.handleMigrateIn(c, inPacket);
                break;
            case USER_HIT:
                c.getChr().getJobHandler().handleHit(c, inPacket);
                break;
            case USER_EMOTION:
                WorldHandler.handleUserEmotion(c, inPacket);
                break;
            case USER_MELEE_ATTACK:
            case USER_SHOOT_ATTACK:
            case USER_MAGIC_ATTACK:
            case USER_NON_TARGET_FORCE_ATOM_ATTACK:
            case USER_AREA_DOT_ATTACK:
                WorldHandler.handleAttack(chr, inPacket, opHeader);
                break;
            case SUMMONED_ATTACK:
                WorldHandler.handleSummonedAttack(c, inPacket);
                break;
            case USER_BAN_MAP_BY_MOB:
                WorldHandler.handleBanMapByMob(c, inPacket);
                break;
            case USER_SELECT_NPC:
                WorldHandler.handleUserSelectNpc(c, inPacket);
                break;
            case USER_SCRIPT_MESSAGE_ANSWER:
                WorldHandler.handleUserScriptMessageAnswer(c, inPacket);
                break;
            case WHISPER:
                WorldHandler.handleWhisper(c, inPacket);
                break;
            case USER_SOUL_EFFECT_REQUEST:
                WorldHandler.handleUserSoulEffectRequest(c, inPacket);
                break;
            case USER_WEAPON_TEMP_ITEM_OPTION_REQUEST:
                WorldHandler.handleUserWeaponTempItemOptionRequest(chr, inPacket);
                break;
            case USER_FINAL_ATTACK_REQUEST:
                WorldHandler.handleUserFinalAttackRequest(c, inPacket);
                break;
            case USER_CHAT:
                WorldHandler.handleUserChat(c, inPacket);
                break;
            case USER_JAGUAR_CHANGE_REQUEST:
                WorldHandler.handleUserJaguarChangeRequest(chr, inPacket);
                break;
            case USER_SHOP_REQUEST:
                WorldHandler.handleUserShopRequest(chr, inPacket);
                break;
            case USER_CHANGE_SLOT_POSITION_REQUEST:
                WorldHandler.handleUserChangeSlotPositionRequest(c, inPacket);
                break;
            case MONSTER_BOOK_MOB_INFO:
                WorldHandler.handleMonsterBookMobInfo(chr, inPacket);
                break;
            case GACHAPON_REQUEST:
                WorldHandler.handleGachaponRequest(chr, inPacket);
                break;
            case USER_CONSUME_CASH_ITEM_USE_REQUEST:
                WorldHandler.handleUserConsumeCashItemUseRequest(c, inPacket);
                break;
            case USER_ITEM_OPTION_UPGRADE_ITEM_USE_REQUEST:
                WorldHandler.handleUserItemOptionUpgradeItemUseRequest(c, inPacket);
                break;
            case USER_ITEM_SLOT_EXTEND_ITEM_USE_REQUEST:
                WorldHandler.handleUserItemSlotExtendItemUseRequest(chr, inPacket);
                break;
            case USER_ADDITIONAL_OPT_UPGRADE_ITEM_USE_REQUEST:
                WorldHandler.handleUserAdditionalOptUpgradeItemUseRequest(c, inPacket);
                break;
            case USER_ITEM_RELEASE_REQUEST:
                WorldHandler.handleUserItemReleaseRequest(c, inPacket);
                break;
            case USER_QUEST_REQUEST:
                WorldHandler.handleUserQuestRequest(c, inPacket);
                break;
            case USER_COMPLETE_NPC_SPEECH:
                WorldHandler.handleUserCompleteNpcSpeech(c, inPacket);
                break;
            case USER_COMPLETE_COMBO_KILL_COUNT_CHECK:
                //WorldHandler.handleUserCombo(c, inPacket);
                break;
            case ZERO_TAG:
                WorldHandler.handleZeroTag(c, inPacket);
                break;
            case PARTY_REQUEST:
                WorldHandler.handlePartyRequest(c, inPacket);
                break;
            case PARTY_RESULT:
                WorldHandler.handlePartyResult(c, inPacket);
                break;
            case GUILD_REQUEST:
                WorldHandler.handleGuildRequest(c, inPacket);
                break;
            case GUILD_JOIN_REQUEST:
                WorldHandler.handleGuildJoinRequest(chr, inPacket);
                break;
            case GUILD_JOIN_ACCEPT:
                WorldHandler.handleGuildJoinAccept(chr, inPacket);
                break;
            case ALLIANCE_REQUEST:
                WorldHandler.handleAllianceRequest(chr, inPacket);
                break;
            case GUILD_BBS:
                WorldHandler.handleGuildBBS(chr, inPacket);
                break;
            case REQUEST_SET_BLESS_OF_DARKNESS:
                WorldHandler.handleRequestSetBlessOfDarkness(c, inPacket);
                break;
            case LOAD_ACCOUNT_ID_OF_CHARACTER_FRIEND_REQUEST:
                WorldHandler.handleLoadAccountIDOfCharacterFriendRequest(c, inPacket);
                break;
            case USER_FIELD_TRANSFER_REQUEST:
                WorldHandler.handleUserFieldTransferRequest(chr, inPacket);
                break;
            case FUNC_KEY_MAPPED_MODIFIED:
                WorldHandler.handleKeymapUpdateRequest(c, inPacket);
                break;
            case SUMMONED_MOVE:
                WorldHandler.handleSummonedMove(chr, inPacket);
                break;
            case SUMMONED_REMOVE:
                WorldHandler.handleSummonedRemove(c, inPacket);
                break;
            case SUMMONED_HIT:
                WorldHandler.handleSummonedHit(c, inPacket);
                break;
            case USER_REQUEST_INSTANCE_TABLE:
                WorldHandler.handleUserRequestInstanceTable(chr, inPacket);
                break;
            case MAKE_ENTER_FIELD_PACKET_FOR_QUICK_MOVE:
                WorldHandler.handleMakeEnterFieldPacketForQuickMove(chr, inPacket);
                break;
            case USER_ABILITY_UP_REQUEST:
                WorldHandler.handleUserAbilityUpRequest(c, inPacket);
                break;
            case USER_ABILITY_MASS_UP_REQUEST:
                WorldHandler.handleUserAbilityMassUpRequest(c, inPacket);
                break;
            case USER_CHANGE_STAT_REQUEST:
                WorldHandler.handleUserChangeStatRequest(c, inPacket);
                break;
            case USER_SKILL_UP_REQUEST:
                WorldHandler.handleUserSkillUpRequest(c, inPacket);
                break;
            case USER_HYPER_SKILL_UP_REQUEST:
                WorldHandler.handleUserHyperSkillUpRequest(chr, inPacket);
                break;
            case USER_BODY_ATTACK:
                WorldHandler.handleBodyAttack(c, inPacket);
                break;
            case USER_CHARACTER_INFO_REQUEST:
                WorldHandler.handleUserCharacterInfoRequest(c, inPacket);
                break;
            case USER_PORTAL_SCROLL_USE_REQUEST:
                WorldHandler.handleUserPortalScrollUseRequest(c, inPacket);
                break;
            case USER_TRANSFER_FIELD_REQUEST:
                WorldHandler.handleChangeFieldRequest(c, inPacket);
                break;
            case USER_PROTECT_BUFF_DIE_ITEM_REQUEST:
                WorldHandler.handleUserProtectBuffDieItemRequest(chr, inPacket);
                break;
            case USER_PORTAL_SCRIPT_REQUEST:
                WorldHandler.handleUserPortalScriptRequest(c, inPacket);
                break;
            case USER_UPGRADE_ITEM_USE_REQUEST:
                WorldHandler.handleUserUpgradeItemUseRequest(c, inPacket);
                break;
            case USER_UPGRADE_ASSIST_ITEM_USE_REQUEST:
                WorldHandler.handleUserUpgradeAssistItemUseRequest(c, inPacket);
                break;
            case USER_DROP_MONEY_REQUEST:
                WorldHandler.handleUserDropMoneyRequest(c, inPacket);
                break;
            case USER_TRANSFER_CHANNEL_REQUEST:
                WorldHandler.handleChangeChannelRequest(c, inPacket);
                break;
            case USER_MOVE:
                WorldHandler.handleUserMove(c, inPacket);
                break;
            case USER_FORCE_ATOM_COLLISION:
                WorldHandler.handleForceAtomCollision(c, inPacket);
                break;
            case LIKE_POINT:
                WorldHandler.handleLikePoint(c, inPacket);
                break;
            case REQUEST_ARROW_PLATER_OBJ:
                WorldHandler.handleRequestArrowPlatterObj(chr, inPacket);
                break;
            case USER_FLAME_ORB_REQUEST:
                WorldHandler.handleUserFlameOrbRequest(chr, inPacket);
                break;
            case CREATE_PSYCHIC_LOCK:
                WorldHandler.handleCreatePsychicLock(chr, inPacket);
                break;
            case RELEASE_PSYCHIC_LOCK:
                WorldHandler.handleReleasePsychicLock(chr, inPacket);
                break;
            case CREATE_KINESIS_PSYCHIC_AREA:
                WorldHandler.handleCreateKinesisPsychicArea(chr, inPacket);
                break;
            case DO_ACTIVE_PSYCHIC_AREA:
                WorldHandler.handleActivePsychicArea(chr, inPacket);
                break;
            case RELEASE_PSYCHIC_AREA:
                WorldHandler.handleReleasePsychicArea(chr, inPacket);
                break;
            case MOB_MOVE:
                WorldHandler.handleMoveMob(c, inPacket);
                break;
            case MOB_APPLY_CTRL:
//                    WorldHandler.handleMobApplyCtrl(c, inPacket);
                break;
            case MOB_SKILL_DELAY_END:
                WorldHandler.handleMobSkillDelayEnd(chr, inPacket);
                break;
            case USER_GROWTH_HELPER_REQUEST:
                WorldHandler.handleUserGrowthRequestHelper(c, inPacket);
                break;
            case SOCKET_CREATE_REQUEST:
                WorldHandler.handleSocketCreateRequest(c, inPacket);
                break;
            case NEBULITE_INSERT_REQUEST:
                WorldHandler.handleNebuliteInsertRequest(c, inPacket);
                break;
            case GOLD_HAMMER_REQUEST:
                WorldHandler.handleGoldHammerRequest(chr, inPacket);
                break;
            case GOLD_HAMMER_COMPLETE:
                WorldHandler.handleGoldHammerComplete(chr, inPacket);
                break;
            case DROP_PICK_UP_REQUEST:
                WorldHandler.handleDropPickUpRequest(c, inPacket);
                break;
            case PARTY_MEMBER_CANDIDATE_REQUEST:
                WorldHandler.handlePartyMemberCandidateRequest(c, inPacket);
                break;
            case PARTY_CANDIDATE_REQUEST:
                WorldHandler.handlePartyCandidateRequest(c, inPacket);
                break;
            case BATTLE_RECORD_ON_OFF_REQUEST:
                WorldHandler.handleBattleRecordOnOffRequest(c, inPacket);
                break;
            case USER_SIT_REQUEST:
                WorldHandler.handleUserSitRequest(chr, inPacket);
                break;
            case USER_PORTABLE_CHAIR_SIT_REQUEST:
                WorldHandler.handleUserPortableChairSitRequest(chr, inPacket);
                break;
            case RW_MULTI_CHARGE_CANCEL_REQUEST:
                WorldHandler.handleRWMultiChargeCancelRequest(c, inPacket);
                break;
            case FOX_MAN_ACTION_SET_USE_REQUEST:
                WorldHandler.handleFoxManActionSetUseRequest(c, inPacket);
                break;
            case QUICKSLOT_KEY_MAPPED_MODIFIED:
                WorldHandler.handleQuickslotKeyMappedModified(chr, inPacket);
                break;
            case DIRECTION_NODE_COLLISION:
                WorldHandler.handleDirectionNodeCollision(c, inPacket);
                break;
            case USER_CREATE_AURA_BY_GRENADE:
                WorldHandler.handleUserCreateAuraByGrenade(c, inPacket);
                break;
            case USER_CREATE_HOLIDOM_REQUEST:
                WorldHandler.handleUserCreateHolidomRequest(c, inPacket);
                break;
            case SUMMONED_SKILL:
                WorldHandler.handleSummonedSkill(c, inPacket);
                break;
            case REACTOR_HIT:
            case REACTOR_CLICK:
                WorldHandler.handleReactorClick(c, inPacket);
                break;
            case REACTOR_RECT_IN_MOB:
                WorldHandler.handleReactorRectInMob(c, inPacket);
                break;
            case MOB_EXPLOSION_START:
          //      WorldHandler.handleMobExplosionStart(c, inPacket);
                break;
            case NPC_MOVE:
                WorldHandler.handleNpcMove(chr, inPacket);
                break;
            case REQUEST_DEC_COMBO:
                WorldHandler.handleRequestDecCombo(c, inPacket);
                break;
            case REQUEST_SET_HP_BASE_DAMAGE:
                WorldHandler.handleRequestSetHpBaseDamage(chr, inPacket);
                break;
            case USER_EQUIPMENT_ENCHANT_WITH_SINGLE_UI_REQUEST:
                WorldHandler.handleUserEquipmentEnchantWithSingleUIRequest(c, inPacket);
                break;
            case USER_SKILL_LEARN_ITEM_USE_REQUEST:
                WorldHandler.handleUserLearnItemUseRequest(c, inPacket);
                break;
            case CASH_SHOP_QUERY_CASH_REQUEST:
                CashShopHandler.handleCashShopQueryCashRequest(c, inPacket);
                break;
            case CASH_SHOP_CASH_ITEM_REQUEST:
                CashShopHandler.handleCashShopCashItemRequest(c, inPacket);
                break;
            case CASH_SHOP_ACTION:
                CashShopHandler.handleCashShopAction(chr, inPacket);
                break;
            case USER_REQUEST_FLYING_SWORD_START:
                WorldHandler.handleUserRequestFlyingSwordStart(c, inPacket);
                break;
            case USER_REQUEST_STEAL_SKILL_LIST:
                WorldHandler.handleUserRequestStealSkillList(c, inPacket);
                break;
            case USER_REQUEST_STEAL_SKILL_MEMORY:
                WorldHandler.handleUserRequestStealSkillMemory(c, inPacket);
                break;
            case USER_REQUEST_SET_STEAL_SKILL_SLOT:
                WorldHandler.handleUserRequestSetStealSkillSlot(c, inPacket);
                break;
            case USER_EX_ITEM_UPGRADE_ITEM_USE_REQUEST:
                WorldHandler.handleUserExItemUpgradeItemUseRequest(c, inPacket);
                break;
            case USER_REQUEST_CHARACTER_POTENTIAL_SKILL_RAND_SET_UI:
                WorldHandler.handleUserRequestCharacterPotentialSkillRandSetUi(chr, inPacket);
                break;
            case USER_CASH_PET_PICK_UP_ON_OFF_REQUEST:
                WorldHandler.handleUserCashPetPickUpOnOffRequest(chr, inPacket);
                break;
            case RUNE_STONE_USE_REQ:
                WorldHandler.handleRuneStoneUseRequest(c, inPacket);
                break;
            case RUNE_STONE_SKILL_REQ:
                WorldHandler.handleRuneStoneSkillRequest(c, inPacket);
                break;
            case SET_SON_OF_LINKED_SKILL_REQUEST:
                WorldHandler.handleSetSonOfLinkedSkillRequest(chr, inPacket);
                break;
            case USER_MEMORIAL_CUBE_OPTION_REQUEST:
                WorldHandler.handleUserMemorialCubeOptionRequest(chr, inPacket);
                break;
            case FAMILIAR_ADD_REQUEST:
                WorldHandler.handleFamiliarAddRequest(chr, inPacket);
                break;
            case FAMILIAR_SPAWN_REQUEST:
                WorldHandler.handleFamiliarSpawnRequest(chr, inPacket);
                break;
            case FAMILIAR_RENAME_REQUEST:
                WorldHandler.handleFamiliarRenameRequest(chr, inPacket);
                break;
            case FAMILIAR_MOVE:
                WorldHandler.handleFamiliarMove(chr, inPacket);
                break;
            case FAMILIAR_ATTACK:
                WorldHandler.handleFamiliarAttack(chr, inPacket);
                break;
            case USER_B2_BODY_REQUEST:
                WorldHandler.handleB2BodyRequest(chr, inPacket);
                break;
            case USER_THROW_GRENADE:
                WorldHandler.handleUserThrowGrenade(chr, inPacket);
                break;
            case USER_DESTROY_GRENADE:
                WorldHandler.handleUserDestroyGrenade(chr, inPacket);
                break;
            case USER_HYPER_STAT_SKILL_UP_REQUEST:
                WorldHandler.handleUserHyperSkillUpRequest(chr, inPacket);
                break;
            case USER_HYPER_STAT_SKILL_RESET_REQUEST:
                WorldHandler.handleUserHyperStatSkillResetRequest(chr, inPacket);
                break;
            case MOB_SELF_DESTRUCT:
                WorldHandler.handleMobSelfDestruct(chr, inPacket);
                break;
            case MOB_AREA_ATTACK_DISEASE:
                WorldHandler.handleMobAreaAttackDisease(chr, inPacket);
                break;
            case PET_MOVE:
                WorldHandler.handlePetMove(chr, inPacket);
                break;
            case PET_DROP_PICK_UP_REQUEST:
                WorldHandler.handlePetDropPickUpRequest(chr, inPacket);
                break;
            case ANDROID_MOVE:
                WorldHandler.handleAndroidMove(chr, inPacket);
                break;
            case USER_CONTENTS_MAP_REQUEST:
           //    WorldHandler.handleUserContentsMapRequest(chr, inPacket);
                break;
            case USER_RUN_SCRIPT:
                WorldHandler.handleUserRunScript(chr, inPacket);
                break;
            case USER_MAP_TRANSFER_REQUEST:
                WorldHandler.handleUserMapTransferRequest(chr, inPacket);
                break;
            case MONSTER_COLLECTION_EXPLORE_REQ:
                WorldHandler.handleMonsterCollectionExploreReq(chr, inPacket);
                break;
            case MONSTER_COLLECTION_COMPLETE_REWARD_REQ:
                WorldHandler.handleMonsterCollectionCompleteRewardReq(chr, inPacket);
                break;
            case GROUP_MESSAGE:
                WorldHandler.handleGroupMessage(chr, inPacket);
                break;
            case ENTER_OPEN_GATE_REQUEST:
                WorldHandler.handleEnterOpenGateRequest(chr, inPacket);
                break;
            case OBTACLE_ATOM_COLLISION:
                // Ignored for now
                break;
            case ENTER_TOWN_PORTAL_REQUEST:
                WorldHandler.handleEnterTownPortalRequest(chr, inPacket);
                break;
            case MINI_ROOM:
                WorldHandler.handleMiniRoom(chr, inPacket);
                break;
            case USER_EFFECT_LOCAL:
                WorldHandler.handleUserEffectLocal(chr, inPacket);
                break;
            case BEAST_TAMER_REGROUP_REQUEST:
                WorldHandler.handleBeastTamerRegroupRequest(chr, inPacket);
                break;
            case USER_GIVE_POPULARITY_REQUEST:
                WorldHandler.handleUserGivePopularityRequest(chr, inPacket);
                break;
            case ENTER_RANDOM_PORTAL_REQUEST:
                WorldHandler.handleEnterRandomPortalRequest(chr, inPacket);
                break;
            case LIBRARY_START_SCRIPT:
                WorldHandler.handleLibraryStartScript(chr, inPacket);
                break;
            case MOB_REQUEST_ESCORT_INFO:
                WorldHandler.handleMobRequestEscortInfo(chr, inPacket);
                break;
            case MOB_ESCORT_COLLISION:
                WorldHandler.handleMobEscortCollision(chr, inPacket);
                break;
            case CROSS_HUNTER_COMPLETE_REQUEST:
                WorldHandler.handleCrossHunterCompleteRequest(chr, inPacket);
                break;
            case CROSS_HUNTER_SHOP_REQUEST:
                WorldHandler.handleCrossHunterShopRequest(chr, inPacket);
                break;
            case USER_MEDAL_REISSUE_REQUEST:
                WorldHandler.handleUserMedalReissueRequest(chr, inPacket);
                break;
            case BROADCAST_EFFECT_TO_SPLIT:
                WorldHandler.handleBroadcastEffectToSplit(chr, inPacket);
                break;
            case BROADCAST_ONE_TIME_ACTION_TO_SPLIT:
                WorldHandler.handleBroadcastOneTimeActionToSplit(chr, inPacket);
                break;
            case MAKING_SKILL_REQUEST:
                WorldHandler.handleMakingSkillRequest(chr, inPacket);
                break;
            case USER_RECIPE_OPEN_ITEM_USE_REQUEST:
                WorldHandler.handleUserRecipeOpenItemUseRequest(chr, inPacket);
                break;
            case USER_FOLLOW_CHARACTER_REQUEST:
                WorldHandler.handleUserFollowCharacterRequest(chr, inPacket);
                break;
            case SET_PASSENSER_RESULT:
                WorldHandler.handleSetPassenserResult(chr, inPacket);
                break;
            case USER_TRANSFER_FREE_MARKET_REQUEST:
                WorldHandler.handleTransferFreeMarketRequest(chr, inPacket);
            case DRAGON_MOVE:
                WorldHandler.handleDragonMove(chr, inPacket);
                break;
            case SPECTRA_ENERGY_UPDATE:
                WorldHandler.handleSpectraUpdate(chr, inPacket);
                break;
            case UPDATE_MATRIX:
                WorldHandler.handleMatrixUpdate(chr, inPacket);
                break;
            case ADMIN:
                AdminHandler.handleAdminCommand(chr, inPacket);
                break;
            case LOG:
                AdminHandler.handleAdminLog(chr, inPacket);
                break;
            case UNITY_PORTAL_REQUEST:
                WorldHandler.handleUnityPortalRequest(chr, inPacket);
                break;
            case BEAST_FORM_WING_OFF:
                WorldHandler.handleBeastFormWingOnOff(chr, inPacket);
                break;
            default:
                handleUnknown(inPacket, op);
                break;
        }
    }

    private void handleUnknown(InPacket inPacket, int opCode) {
        if(!InHeader.isSpamHeader(InHeader.getInHeaderByOp(opCode))) {
            log.warn(String.format("Unhandled opcode %s/0x%s, packet %s", opCode, Integer.toHexString(opCode).toUpperCase(), inPacket));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (cause instanceof IOException) {
            log.info("Client forcibly closed the game.");
        } else {
            cause.printStackTrace();
        }
    }
}
