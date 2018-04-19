package heigvd.res.labo04.prank;
import heigvd.res.labo04.model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class Prank extends Mail {
    private Group  group = new Group();
    private String subject;
    private String data;
    private int groupSize;

    public Prank(Group group, String subject, String data) throws IOException, DataFormatException {
        super(group,subject,data);
    }
}
