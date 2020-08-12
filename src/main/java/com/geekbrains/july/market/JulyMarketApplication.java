package com.geekbrains.july.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JulyMarketApplication {
	// Домашнее задание:
	// 1. Чуть-чуть разберитесь с AngularJS
	// 2. Сделайте отображение всех товаров в таблицу
	// через REST API
	// 3. Сделайте так, чтобы через форму можно было добавлять товары
	// * 4. Сделайте basic authentication для REST API

	public static void main(String[] args) {
		SpringApplication.run(JulyMarketApplication.class, args);
	}
}