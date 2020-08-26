package com.geekbrains.july.market.utils.logging;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@Aspect
@Component
public class AppLoggingAspect {

    @After("execution(public * com.geekbrains.july.market.services.ProductsService.saveOrUpdate(..))") // pointcut expression
    public void beforeProductSaveOrUpdate(JoinPoint joinPoint) {

        Date date = new Date();
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
                System.out.println(date.toString()  + " Product: " + args[0] + " was saved or updated");
                writeLog (date.toString()  + " Product: " + args[0] + " was saved or updated");
        }
    }

    @After("execution(public void com.geekbrains.july.market.services.ProductsService.deleteById(..))") // pointcut expression
    public void beforeProductDelete(JoinPoint joinPoint) {
        Date date = new Date();
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            System.out.println(date.toString()  + " Product: " + args[0] + " was deleted");
            writeLog (date.toString()  + " Product: " + args[0] + " was deleted");
        }
    }

    public void writeLog(String logString){
        try(FileWriter writer = new FileWriter("history.txt", false))
        {
            writer.write(logString);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

    }
}
