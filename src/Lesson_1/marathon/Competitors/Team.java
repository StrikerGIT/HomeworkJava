package Lesson_1.marathon.Competitors;

public class Team {
    String Name;
    public Competitor[] ArrayOfCompetitors = {new Human("Боб"), new Human("Вася"), new Cat("Барсик"), new Dog("Бобик")};

    public Team(String TeamName) {Name = TeamName;}

    public void InfoTeam() {
        System.out.println("Команда " + Name + " приступила к соревнованиям!");
        for (Competitor Comp : ArrayOfCompetitors)
        {
            System.out.print("В нашей команде участвует ");
            Comp.infoName();
        }
        System.out.println("__________________________________________");
        }

    public void InfoResult() {
        System.out.println("__________________________________________");
        System.out.println("Результаты команды: " + Name);
        for (Competitor Comp : ArrayOfCompetitors)
        {
            Comp.infoResult();
        }
        System.out.println("__________________________________________");
    }
        }




