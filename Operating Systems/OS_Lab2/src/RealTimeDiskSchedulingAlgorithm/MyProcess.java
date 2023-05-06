package RealTimeDiskSchedulingAlgorithm;

import java.util.Comparator;

public class MyProcess implements Cloneable {
    int deadline;
    int arrivalTime;
    int track;

    public MyProcess(int arrivalTime, int track, int deadline) {
        this.deadline = deadline;
        this.arrivalTime = arrivalTime;
        this.track = track;
    }

    @Override
    protected MyProcess clone() {
        try {
            return (MyProcess)super.clone();
        } catch(Exception e){
            return null;
        }
    }

    public static Comparator<MyProcess> DeadlineComp = new Comparator<MyProcess>(){
        public int compare(MyProcess a1, MyProcess a2){
            return Integer.compare(a1.getDeadline(), a2.getDeadline());
        }
    };

    public static Comparator<MyProcess> ArrivalTimeComp = new Comparator<MyProcess>(){
        public int compare(MyProcess p1, MyProcess p2){
            return Integer.compare(p1.arrivalTime, p2.arrivalTime);
        }
    };

    public int  getDeadline() { return deadline;}
    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }
    public int getTrack() {
        return track;
    }
    public void setTrack(int track) {
        this.track = track;
    }
    public int  getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

}

