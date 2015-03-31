package in.sn.com.entity;

/**
 * Created by sumanta on 30/3/15.
 */
public class SubjectEntity {
    private int subjectImageId;
    private String subjectName;
    public SubjectEntity(int subjectImageId, String subjectName) {
        this.subjectImageId = subjectImageId;
        this.subjectName = subjectName;
    }
    public int getSubjectImageId() {
        return subjectImageId;
    }
    public String getSubjectName() {
        return subjectName;
    }
}
