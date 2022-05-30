package madrat.Empire;

public class ConsolePublisher implements IPublisher {

    private boolean showFights;
    private boolean showLogs;
    private boolean showFactory;

    @Override
    public void showFights(boolean status) {
        showFights = status;
    }

    @Override
    public void showLogs(boolean status) {
        showLogs = status;
    }

    @Override
    public void showFactory(boolean status) {
        showFactory = status;
    }

    @Override
    public void publish() {
        while (!MessageBox.isEmpty()) {
            String mes = MessageBox.getMessage();
            if (!showLogs && mes.startsWith("[LOG]")) {
                continue;
            }
            if (!showFights && mes.startsWith("[FIGHT]")) {
                continue;
            }
            if (!showFactory && mes.startsWith("[FACTORY]")) {
                continue;
            }
            System.out.println(mes);
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
    public void publishFights() {
        while (!MessageBox.isEmpty()) {
            String mes = MessageBox.getMessage();
            if (mes.startsWith("[FIGHT]")) {
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
