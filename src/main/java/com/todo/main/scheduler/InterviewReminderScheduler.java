package com.todo.main.scheduler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.todo.main.model.Todo;
import com.todo.main.repository.TodoRepository;
import com.todo.main.service.MailService;

@Component
public class InterviewReminderScheduler 
{

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private MailService mailService;

    @Scheduled(cron = "0 0 9 * * *") // runs at 9:00 AM every day
    public void sendDailyInterviewReminderAt9AM() 
    {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDate.now().atTime(23, 59, 59);

        List<Todo> todayInterviews = todoRepository.findByInterviewDateBetween(start, end);
        StringBuilder content = new StringBuilder("<html><body style='background-color:#1C2833; font-family: Arial;'>");

        if (!todayInterviews.isEmpty()) 
        {
            content.append("<h2 style='color:#F4D03F;'>Today's Interviews</h2>");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

            int index = 1;
            for (Todo t : todayInterviews) 
            {
                content.append("<div style='margin-bottom: 25px; margin-left: 20px;'>")
                    .append("<div style='font-size:20px; color:#F4D03F; margin-bottom:5px;'>").append(index++).append(".</div>")
                    .append("<div style='font-size:18px;'><span style='color:white;'>Name:</span> <span style='color:#58D68D;'>")
                    .append(t.getName()).append("</span></div>")
                    .append("<div style='font-size:18px;'><span style='color:white;'>Interview Time:</span> <span style='color:#58D68D;'>")
                    .append(t.getInterviewDate().format(formatter)).append("</span></div>")
                    .append("<div style='font-size:18px;'><span style='color:white;'>Reason:</span> <span style='color:#58D68D;'>")
                    .append(t.getReason()).append("</span></div>")
                    .append("</div>");
            }
        }
        else 
        {
            content.append("<p style='color:white;'>No interviews scheduled today.</p>");
        }

        content.append("</body></html>");
        
        mailService.sendInterviewListToAdmin("Today's Interviews", content.toString(), true);
        
        
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

        //student
//        for (Todo t : todayInterviews) 
//        {
//            if (t.getEmail() != null && !t.getEmail().isBlank()) 
//        	{
//                String subject = "Interview Scheduled for Today";
//                String message = "<html><body style='background-color:#1C2833; font-family: Arial; color:white;'>"
//                        + "<h2 style='color:#F4D03F;'>Hi " + t.getName() + ",</h2>"
//                        + "<p>Your interview is scheduled today at <strong style='color:#58D68D;'>"
//                        + t.getInterviewDate().format(timeFormatter) + "</strong>.</p>"
//                        + "<p><strong>Reason:</strong> " + t.getReason() + "</p>"
//                        + "<p>Best of luck!</p>"
//                        + "</body></html>";
//
//                mailService.sendInterviewReminderToUser(subject, message, t.getEmail(), true);
//            }
//        }
    }

    
    @Scheduled(cron = "0 * * * * *") // runs every minute
    public void notifyOnExactInterviewTime()
    {
        LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);

        List<Todo> allToday = todoRepository.findByInterviewDateBetween(
                now.toLocalDate().atStartOfDay(),
                now.toLocalDate().atTime(23, 59, 59));

        List<Todo> matchingInterviews = allToday.stream()
                .filter(t -> t.getInterviewDate() != null &&
                             t.getInterviewDate().withSecond(0).withNano(0).equals(now))
                .toList();

        if (!matchingInterviews.isEmpty()) 
        {
            StringBuilder content = new StringBuilder("<html><body style='background-color:#1C2833; font-family: Arial;'>");

            content.append("<h2 style='color:#F4D03F;'>Interview Reminder - ").append(now.format(DateTimeFormatter.ofPattern("hh:mm a"))).append("</h2>");

            int num = 1;
            for (Todo t : matchingInterviews) 
            {
                content.append("<div style='margin-bottom: 25px; margin-left: 20px;'>")
                    .append("<div style='font-size:20px; color:#F4D03F; margin-bottom:5px;'>").append(num++).append(".</div>")
                    .append("<div style='font-size:18px;'><span style='color:white;'>Name:</span> <span style='color:#58D68D;'>")
                    .append(t.getName()).append("</span></div>")
                    .append("<div style='font-size:18px;'><span style='color:white;'>Interview Time:</span> <span style='color:#58D68D;'>")
                    .append(t.getInterviewDate().format(DateTimeFormatter.ofPattern("hh:mm a"))).append("</span></div>")
                    .append("<div style='font-size:18px;'><span style='color:white;'>Reason:</span> <span style='color:#58D68D;'>")
                    .append(t.getReason()).append("</span></div>")
                    .append("</div>");
            }

            content.append("</body></html>");

            mailService.sendInterviewListToAdmin("Interview Reminder - " + now.format(DateTimeFormatter.ofPattern("hh:mm a")),
                                                 content.toString(),
                                                 true);
            
            for (Todo t : matchingInterviews) 
            {
                if (t.getEmail() != null && !t.getEmail().isBlank()) 
                {
                    mailService.sendInterviewReminderToUser("Interview Reminder - " + now.format(DateTimeFormatter.ofPattern("hh:mm a")),
                                                            content.toString(),
                                                            t.getEmail(),
                                                            true);
                }
            }
        }
    }
    
}
