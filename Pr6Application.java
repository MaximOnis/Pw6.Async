package com.example.pr6;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@EnableAsync // Дозволяє асинхронні операції
@EnableScheduling // Дозволяє планування завдань
public class Pr6Application {

	public static void main(String[] args) {
		SpringApplication.run(Pr6Application.class, args);
	}
}

@Component
class AsyncTasks {
	int a = 2000;

	private AtomicInteger counter = new AtomicInteger(0);

	// Збільшуємо лічильник кожні 2 секунди
	@Scheduled(fixedRate = 2000)
	public void increaseCounter() {
		if (counter.get() < 10) {
			int value = counter.incrementAndGet();
			System.out.println("Current Counter: " + value);
			if (value == 10) {
				System.out.println("Counter reached 10. Stopping...");
			}
		}
	}

	// Виводимо поточний час кожні 5 секунд
	@Scheduled(fixedRate = 5000)
	public void printCurrentTime() {
		if (counter.get() < 10) {
			System.out.println("Current Time: " + System.currentTimeMillis());
		}
	}
}

@Component
class CommandLineAppStartupRunner implements CommandLineRunner {

	@Override
	public void run(String...args) throws Exception {
		// Програма працює до завершення
		System.out.println("Application started!");
	}
}
