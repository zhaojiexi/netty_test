package com.game.textwebsocket;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author zhaojx
 * @date 2023/9/1 10:35
 */
public enum ScheduleUtil {
  INSTANCE;

  ScheduledExecutorService pool = new ScheduledThreadPoolExecutor(1,
      r -> new Thread(r, "ScheduleUtil"));

  public void scheduleAtFixedRateWithMills(Runnable command, long initialDelay, long period) {
    pool.scheduleAtFixedRate(command, initialDelay, period,
        java.util.concurrent.TimeUnit.MILLISECONDS);
  }
}
