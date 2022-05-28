package madrat.Empire;

public class ConsolePublisher implements IPublisher {

    @Override
    public void publish() {
        while (!MessageBox.isEmpty()) {
            String mes = MessageBox.getMessage();
            if (!mes.startsWith("[LOG]")) {
                System.out.println(mes);
            }
        }
    }

    @Override
    public void publishLogs() {
        while (!MessageBox.isEmpty()) {
            String mes = MessageBox.getMessage();
            if (mes.startsWith("[LOG]")) {
                System.out.println(mes);
            }
        }
    }

    @Override
    public void publishAll() {
        while (!MessageBox.isEmpty()) {
            System.out.println(MessageBox.getMessage());
        }
    }
}
