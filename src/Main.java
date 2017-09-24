import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * TODO: declaration class Chat
     * TODO: declaration class Actor
     * TODO: declaration class Bot extend Actor
     */

    public class Chat {

        private String RoomName;
        private double ChatID;
        private String GreatingMessage;

        public void chatGreating() {
            GreatingMessage = loadInfoMessage();
            System.out.println(GreatingMessage);
            System.out.println(getRoomName());
            System.out.println(getChatID());
        }

        public void sendMessage() {

        }

        private void operationControl() {

        }

        private String loadInfoMessage() {
            final String DEFAULT_MESSAGE = "__________Hi, everybody!__________";
            String InfoMessage = DEFAULT_MESSAGE;
            return InfoMessage;
        }

        public String getRoomName() {
            return RoomName;
        }

        public void setRoomName(String roomName) {
            this.RoomName = roomName;
        }

        public double getChatID() {
            return ChatID;
        }

        public void setChatID(double chatID) {
            this.ChatID = chatID;
        }
    }

    public class Actor {
        private String Nickname;

        public void setNickname(String nickname) {
            this.Nickname = nickname;
        }

        public String getNickname() {
            return Nickname;
        }

        public String sendMessage() {
            System.out.print(this.Nickname + ":> ");

            Scanner inputMessage = new Scanner(System.in);
            String Message = inputMessage.nextLine();

            return Message;
        }
    }

    public class Bot extends Actor {
//        List answerList = new ArrayList<>();
//        String[] AnswerList;
        ArrayList AnswerList = new ArrayList();

        public void greetingPhrase() {
            System.out.println(AnswerList.get(0));
        }

        public void farewellPhrase() {
            System.out.println(AnswerList.get(AnswerList.size()));
        }

        public String sendMessage() {
            System.out.print(this.getNickname() + ":> ");

            int rnd = 1 + (int) (Math.random() * (this.AnswerList.size() - 1));

            String Message = (String) this.AnswerList.get(rnd);

            return Message;
        }

        public void answerBot(ArrayList listOfAnswer) {
            int rnd = 1 + (int) (Math.random() * (listOfAnswer.size() - 1));
            System.out.println(listOfAnswer.get(rnd));
        }

        public Bot(String fileOfAnswer) {
            Scanner inputFile = null;
            try {
                inputFile = new Scanner(new BufferedReader(new FileReader(fileOfAnswer)));
            } catch (FileNotFoundException e) {
                System.out.println("file answer.txt not found! Please, create this file.");
            }
            while (inputFile.hasNextLine()) {
                this.AnswerList.add(inputFile.nextLine());
            }
            inputFile.close();
        }
    }

    public static void main(String[] args) {

        Scanner inputCli = new Scanner(System.in);

        Chat chatInstance = new Main().new Chat();
        chatInstance.setRoomName("********=======-----Common Room-----=======********");
        chatInstance.setChatID(Math.random());
        chatInstance.chatGreating();

        Actor human = new Main().new Actor();
        System.out.print("Input your name: ");
        String nick = inputCli.next();
        human.setNickname(nick);

        Bot chatBot = new Main().new Bot("answer.txt");
        chatBot.setNickname("BOT-");// + (String) Math.random());

        human.sendMessage();
        String a = chatBot.sendMessage();
        System.out.println(a);
    }
}
