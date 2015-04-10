package in.sn.com.entity;

/**
 * Created by sumanta on 30/3/15.
 */
public class SubjectEntity {
    private int subjectImageId;
    private String subjectName;
    private String subjectCode;
    public SubjectEntity(int subjectImageId, String subjectName, String subjectCode) {
        this.subjectImageId = subjectImageId;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
    }
    public int getSubjectImageId() {
        return subjectImageId;
    }
    public String getSubjectName() {
        return subjectName;
    }
    public String getSubjectCode() {
        return subjectCode;
    }
}
