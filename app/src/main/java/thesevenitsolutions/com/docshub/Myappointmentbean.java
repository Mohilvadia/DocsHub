package thesevenitsolutions.com.docshub;

class Myappointmentbean {
    private String date,time,patientname,drname,remarks;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getDrname() {
        return drname;
    }

    public void setDrname(String drname) {
        this.drname = drname;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Myappointmentbean(String date, String time, String patientname, String drname, String remarks) {
        this.date = date;
        this.time = time;
        this.patientname = patientname;
        this.drname = drname;
        this.remarks = remarks;
    }
}
