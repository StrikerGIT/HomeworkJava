package com.geekbrains.july.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class JulyMarketApplication {
	// Домашнее задание:
	// 1. Вам нужно оборачивать исключения на backend в json и отправлять на
	// фронтенд. Обработайте таким образом ситуацию с попыткой редактирования
	// товара с несуществующим id. Если пользователь попытается это сделать
	// ему надо показать понятный alert
	// 2. С помощью spring aop выведите в файл history.txt строки с описанием
	// того в какое время создавались, удалялись или модифицировались
	// товары или категории товаров, и желательно выводить их навзвание
	// *. Попробуйте перенести спецификации на AJS


	public static void main(String[] args) {
		SpringApplication.run(JulyMarketApplication.class, args);
	}
}