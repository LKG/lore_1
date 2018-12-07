package im.heart.material.parser.impl;

import im.heart.material.entity.MaterialPeriodical;
import im.heart.material.parser.MaterialParser;
import org.springframework.scheduling.annotation.Async;

import java.io.InputStream;

public class MaterialInfoParserImpl implements MaterialParser {
    @Override
    public void parser(MaterialPeriodical periodical, InputStream is) {

    }

    @Async
    @Override
    public void addParserTask(MaterialPeriodical periodical, InputStream is) {

    }
}
