package madrat.Empire;

public interface IPublisher {

    void showFights(boolean status);
    void showLogs(boolean status);
    void publish();
    void publishLogs();
    void publishAll();
    void publishFights();
}
