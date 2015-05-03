package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.CorrelationStrategy;
import org.springframework.integration.annotation.ReleaseStrategy;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * Created by jellin on 5/1/15.
 */
@Component
public class TestAggregator {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    /**
     * Use the JobId in the file to group messages tpgether
     * @param id
     * @return
     */
    @CorrelationStrategy
    public String correlateBy(@Payload("name") String id) {
        log.debug("Payload of message is {}", id);
        String correllationId = getFileJobid(id);
        log.debug("Correlation Id is {}",correllationId);
        return correllationId;
    }


    /**
     * only release files if we have received them both.
     * @param messages
     * @return
     */
    @ReleaseStrategy
    public boolean release(List<Message<File>> messages) {
        log.debug("Message Size is {}",messages.size());
        return messages.size() == 2;
    }

    public List<File> aggregateFiles(List<File> messages){
        log.debug("Aggregating Files");
        return messages;
    }

    private String getFileJobid(String s) {
        int uf = s.indexOf("_._");
        int ul = s.lastIndexOf("_");
        return s.substring(uf + 3, ul);
    }
}
