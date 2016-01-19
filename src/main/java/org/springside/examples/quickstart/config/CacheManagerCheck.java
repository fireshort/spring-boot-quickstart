package org.springside.examples.quickstart.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * Created by Ivan on 2016/1/18.
 */
@Component
public class CacheManagerCheck implements CommandLineRunner {

    private static final Logger logger = LoggerFactory
            .getLogger(CacheManagerCheck.class);

    private final CacheManager cacheManager;

    @Autowired
    public CacheManagerCheck(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("\n\n"
                + "=========================================================\n"
                + "Using cache manager: " + this.cacheManager.getClass().getName() + "\n"
                + "=========================================================\n\n");
    }

}
