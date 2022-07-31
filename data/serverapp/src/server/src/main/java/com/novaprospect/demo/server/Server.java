package com.novaprospect.demo.server;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import java.lang.Double;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.stereotype.Component;

@Component
public class Server {

	private final AtomicReference<Double> metricValue = new AtomicReference<>(0.0);
	private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

	public Server(MeterRegistry meterRegistry) {
		executor.scheduleAtFixedRate(() -> { metricValue.set(Math.random()); }, 0, 1, TimeUnit.SECONDS);
		Gauge.builder("demo.metric", metricValue::get)
			.register(meterRegistry);
	}
}
