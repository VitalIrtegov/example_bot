import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

public class MyTelegramBot extends TelegramLongPollingBot {
    public static final String BOT_TOKEN = "5768784033:AAHy3dCJgE5C-bfkExt-s3pczIJvM8EITC0";

    public static final String BOT_USERNAME = "VitalNasaAmazing_Bot";

    public static final String URL = "https://api.nasa.gov/planetary/apod?api_key=gUW0BP1YOCZgdgzh2ibyQET5iOuVFuZYrGegKs8O";

    public static final String CHAT_ID = "1977466173";

    public MyTelegramBot() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            botsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            switch (update.getMessage().getText()) {
                case "/help":
                    sendMessage("Привет, я бот NASA! Я высылаю ссылки на картинки по запросу. " +
                            "Напоминаю, что картинки на сайте NASA обновляются раз в сутки");
                    break;
                case "/give":
                    try {
                        sendMessage(Utils.getUrl(URL));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
        }
    }

    private void sendMessage(String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(CHAT_ID);
        message.setText(messageText);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
