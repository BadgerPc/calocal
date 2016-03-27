package vajracode.calocal.client.i18n;

import com.google.gwt.i18n.client.Constants;

public interface I18nConstants extends Constants{

	@DefaultStringValue("Ok")
	String ok();

	@DefaultStringValue("Cancel")
	String cancel();

	@DefaultStringValue("Application updated: refresh this page")
	String updateApplication();

	@DefaultStringValue("Network error")
	String networkError();
	
	@DefaultStringValue("Unknown error")
	String unknownError();

	@DefaultStringValue("Sign in please")
	String pleaseSignIn();
	
	@DefaultStringValue("Проблемы авторизации, попробуйте ещё раз.")
	String userDenied();
	
	@DefaultStringValue("Что вы хотите отдать?")
	String itemTitle();
	
	@DefaultStringValue("Добавьте описание")
	String itemDesc();
	
	@DefaultStringValue("Что вы ищете?")
	String wishTitle();
	
	@DefaultStringValue("Добавьте описание")
	String wishDesc();
	
	@DefaultStringValue("Искать")
	String search();

	@DefaultStringValue("Мои вещи")
	String myItems();

	@DefaultStringValue("Пусто")
	String empty();
	
	@DefaultStringValue("Отдать")
	String create();

	@DefaultStringValue("Подключить")
	String bind();

	@DefaultStringValue("Подробнее")
	String details();

	@DefaultStringValue("Взять")
	String take();

	@DefaultStringValue("Удалить")
	String delete();

	@DefaultStringValue("Отдать")
	String fulfill();
	
	@DefaultStringValue("Сообщения")
	String routine();	
	
	@DefaultStringValue("Входящие")
	String requestsToMe();
	
	@DefaultStringValue("Исходящие")
	String requestsFromMe();

	@DefaultStringValue("Забирает...")
	String given();

	@DefaultStringValue("Запрос от")
	String requestFrom();

	@DefaultStringValue("Можно забирать!")
	String canTake();

	@DefaultStringValue("Ждет ответа...")
	String waitingForResponce();

	@DefaultStringValue("Назад")
	String back();

	@DefaultStringValue("Друг моего друга")
	String unknown();

	@DefaultStringValue("Предметы")
	String userItems();	
	
	@DefaultStringValue("Вишлист")
	String wishlist();	
	
	@DefaultStringValue("Друзья")
	String friends();
	
	@DefaultStringValue("друзей")
	String friends2();
	
	@DefaultStringValue("Мой кабинет")
	String profile();
	
	@DefaultStringValue("Выйти")
	String signOut();
	
	@DefaultStringValue("Войти")
	String signIn();
	
	@DefaultStringValue("Группы")
	String groups();

	@DefaultStringValue("участников")
	String participant0();
	
	@DefaultStringValue("участник")
	String participant1();
	
	@DefaultStringValue("участника")
	String participant2();

	@DefaultStringValue("Не показывать желания")
	String dontShowWishes();

	@DefaultStringValue("Участники")
	String participants();

	@DefaultStringValue("Пригласить")
	String invite();
	
	@DefaultStringValue("Пригласить друзей")
	String inviteFriends();
	
	@DefaultStringValue("Мои желания")
	String myWishes();
	
	@DefaultStringValue("Владелец")
	String owner();
	
	@DefaultStringValue("Только владелец")
	String ownerOnly();
	
	@DefaultStringValue("Любой участник")
	String anyParticipant();

	@DefaultStringValue("Приглашает")
	String invites();

	@DefaultStringValue("Вступить")
	String join();

	@DefaultStringValue("Покинуть группу?")
	String quitGroupHeader();

	@DefaultStringValue("Если вы покинете эту группу, то потом не сможете вступить в неё снова без приглашения.")
	String quitGroupDesc();

	@DefaultStringValue("Держи - самый дружелюбный способ брать и отдавать")
	String invitation();

	@DefaultStringValue("Редактировать")
	String edit();
	
	@DefaultStringValue("Редактировать группу")
	String editGroup();
	
	@DefaultStringValue("Предложить предмет")
	String offerItem();
	
	@DefaultStringValue("Новый")
	String neww();
	
	@DefaultStringValue("Существующий")
	String existing();

	@DefaultStringValue("Моё")
	String mine();

	@DefaultStringValue("Мои друзья")
	String myFriends();

	@DefaultStringValue("Выберите, в каких группах будет доступно объявление")
	String chooseItemGroups();

	@DefaultStringValue("Предложить")
	String offer();

	@DefaultStringValue("Предложение от")
	String offerFrom();

	@DefaultStringValue("Связаться через")
	String sendPm();

	@DefaultStringValue("Отменён")
	String canceled();

	@DefaultStringValue("для ")
	String forr();

	@DefaultStringValue("отдаёт")
	String givesAway();

	@DefaultStringValue("Предмет уже отдан")
	String itemAlreadyGiven();

	@DefaultStringValue("Отменил передачу")
	String canceledTransaction();

	@DefaultStringValue("Я передумал")
	String iChangedMind();

	@DefaultStringValue("Отказаться")
	String refuse();

	@DefaultStringValue("изменил статус на")
	String changedStatus();

	@DefaultStringValue("Держи!")
	String derzhi();

	@DefaultStringValue("ищет")
	String looksFor();

	@DefaultStringValue("среди ")
	String from();

	@DefaultStringValue("может одолжить")
	String canLend();

	@DefaultStringValue("ищет на время")
	String looksForTemp();

	@DefaultStringValue("одолжено")
	String lentComplete();

	@DefaultStringValue("отдано")
	String giveComplete();

	@DefaultStringValue("одолжил этот предмет")
	String lentThisItem();

	@DefaultStringValue("возвращено")
	String returnComplete();

	@DefaultStringValue("Подходящие предметы")
	String matchingItems();

	@DefaultStringValue("Новое объявление")
	String newAd();

	@DefaultStringValue("Редактирование объявления")
	String editAd();
	
	@DefaultStringValue("Например, велосипед или телефон")
	String itemTitleEx();
	
	@DefaultStringValue("Укажите основные плюсы вещи, и ваши друзья поймут, что это именно то, чего им не хватало!")
	String itemDescEx();
	
	@DefaultStringValue("Например, велосипед или телефон")
	String wishTitleEx();
	
	@DefaultStringValue("Укажите какую именно вещь вы ищите, когда собираетесь вернуть и т.п.")
	String wishDescEx();

	@DefaultStringValue("Отдать на время")
	String itemTemp();

	@DefaultStringValue("Взять на время")
	String wishTemp();

	@DefaultStringValue("Отметьте, если вы планируете когда-нибудь забрать предмет обратно")
	String itemTempEx();

	@DefaultStringValue("Отметьте, если вы потом планируете вернуть предмет")
	String wishTempEx();

	@DefaultStringValue("Добавить группу")
	String addGroup();
	
	@DefaultStringValue("Добавить в группу новый предмет или желание. Добавить уже существующий можно через редактирование предмета.")
	String addItemOrWish();
	
	@DefaultStringValue("Добавьте фото")
	String addImage();

	@DefaultStringValue("С фотографией оценить вашу вещь будет гораздо проще")
	String itemImageEx();

	@DefaultStringValue("фотография поможет лучше понять, чего вы хотите")
	String wishImageEx();

	@DefaultStringValue("Кто может видеть объявление?")
	String whoCanSee();
	
	@DefaultStringValue("Участников")
	String participants2();
	
	@DefaultStringValue("Вещей")
	String items2();
	
	@DefaultStringValue("Желаний")
	String wishes2();
	
	@DefaultStringValue("Объявления")
	String ads();
	
	@DefaultStringValue("Предмет")
	String item();
	
	@DefaultStringValue("Желание")
	String wish();
	
	@DefaultStringValue("Название")
	String title();
	
	@DefaultStringValue("Описание")
	String description();

	@DefaultStringValue("Написать")
	String write();

	@DefaultStringValue("Принять")
	String accept();

	@DefaultStringValue("Беру")
	String readyToTake();

	@DefaultStringValue("Почта")
	String mail();

	@DefaultStringValue("Письмо")
	String letter();

	@DefaultStringValue("Нет доступа")
	String noAccess();
	
	@DefaultStringValue("Текст сообщения")
	String messageText();
	
	@DefaultStringValue("Одолженное")
	String lent();

	@DefaultStringValue("одолжил попользоваться")
	String lentTemp();

	@DefaultStringValue("Открыть")
	String open();

	@DefaultStringValue("Оплатить")
	String pay();

	@DefaultStringValue("Не нашли такого, не знаю какого...")
	String error404();

	@DefaultStringValue("Объеденить")
	String unite();
	
	@DefaultStringValue("Внимание!")
	String warning();

	@DefaultStringValue("Одалживается на время")
	String temporary();
	
	@DefaultStringValue("Покинуть группу")
	String leaveGroup();
	
	@DefaultStringValue("Новый предмет или желание")
	String newItemOrWish();
	
	@DefaultStringValue("Жмёшь и <b>ищешь</b>!")
	String helpSearch();

	@DefaultStringValue("Удаление")
	String deletion();

	@DefaultStringValue("Вы уверены, что хотите удалить это объявление")
	String confirmAdDeletion();

	@DefaultStringValue("Если вы покинете эту страницу, все несохраненные данные будут утеряны. Продолжить?")
	String confirmFragile();

	@DefaultStringValue("Нет, остаться")
	String noStay();

	@DefaultStringValue("Сообщение успешно отправлено")
	String sendSuccess();

	@DefaultStringValue("Ошибка")
	String error();

	@DefaultStringValue("Нужна эта вещь?<br>Отправьте владельцу сообщение!")
	String itemProfileCanTake();
	
	@DefaultStringValue("Привязать ВКонтакте")
	String bindVk();
	
	@DefaultStringValue("Привязать Facebook")
	String bindFb();
	
	@DefaultStringValue("Привязать эл.почту")
	String bindEmail();

	@DefaultStringValue("одолжил(а) у ")
	String lend();
	
	@DefaultStringValue("Картинка группы")
	String addGroupImage();
	
	@DefaultStringValue("(дальше...)")
	String more();

	@DefaultStringValue("Кто-то хочет забрать ваш предмет! Если вы согласны его отдать, нажмите на кнопку <b>ДЕРЖИ!</b>")
	String irqMyItemAccepted();

	@DefaultStringValue("Поздравляем! Вы согласились отдать предмет другу. Когда вы передадите предмет, нажмите на кнопку <b>ОТДАНО</b>, чтобы закрыть объявление.")
	String irqMyItemPending();

	@DefaultStringValue("Ваш друг передумал забирать этот предмет. Отдайте его кому-нибудь другому!")
	String irqMyItemCanceled();
	
	@DefaultStringValue("Вы передумали отдавать предмет вашему другу. Отдайте его кому-нибудь другому!")
	String irqMyItemCanceledOwner();
	
	@DefaultStringValue("Ура! Владелец подтвердил, что предмет можно забирать. Свяжитесь с ним любым из доступных вам каналов связи. Удачи!")
	String irqItemAccepted();

	@DefaultStringValue("Вы оставили заявку на предмет. Дождитесь, пока владелец вам ответит!")
	String irqItemPending();

	@DefaultStringValue("Вы передумали забирать предмет.")
	String irqItemCanceled();
	
	@DefaultStringValue("К сожалению, владелец передумал отдавать вам предмет.")
	String irqItemCanceledOwner();
	
	@DefaultStringValue("Вы сообщили другу, что заинтересовались его предложением. Свяжитесь с ним любым из доступных вам каналов связи. Когда вы получите предмет, нажмите на кнопку “Найдено!”, чтобы закрыть объявление.")
	String irqMyWishAccepted();

	@DefaultStringValue("Кто-то оставил вам сообщение. Если это то, что вам нужно, нажмите на кнопку <b>Беру!</b>")
	String irqMyWishPending();

	@DefaultStringValue("Ваш друг передумал. Возможно, искомая вещь найдется у кого-нибудь другого!")
	String irqMyWishCanceled();
	
	@DefaultStringValue("Вы передумали забирать предмет у вашего друга.")
	String irqMyWishCanceledOwner();
	
	@DefaultStringValue("Вы оставили сообщение к объявлению. Дождитесь, пока владелец вам ответит!")
	String irqWishAccepted();

	@DefaultStringValue("Ваш друг заинтересовался вашим предложением. Свяжитесь с ним любым из доступных вам каналов связи. Удачи!")
	String irqWishPending();

	@DefaultStringValue("Вы передумали отдавать предмет.")
	String irqWishCanceled();
	
	@DefaultStringValue("Ваш друг перестал быть заинтересован в вашем предложении.")
	String irqWishCanceledOwner();

	@DefaultStringValue("Владелец сообщил, что предмет уже отдан другому пользователю.")
	String irqItemGiven();

	@DefaultStringValue("подумывает забрать")
	String ircRequestedItem();

	@DefaultStringValue("предлагает")
	String ircOfferedItem();

	@DefaultStringValue("Связаться")
	String connect();

	
}
