package demo;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * Created by jellin on 5/1/15.
 */
@Component
public class TestService {


    public List<File>  handleFile(List<File> files){

        System.err.println(files);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return files;

    }

}
