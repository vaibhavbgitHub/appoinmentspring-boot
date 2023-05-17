package com.example.controller;
import com.example.entity.Event;
import com.example.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventRepository eventRepository;
    @PostMapping()
    public String saveData(@RequestBody Event event){
        eventRepository.save(event);
        return "data save successfully....";
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDto> getDataByID(@PathVariable Long eventId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);

        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
                 String name = event.getName();
            LocalDateTime startTime = event.getStartTime();

            LocalDateTime endTime = startTime.plusMinutes(10);

            EventDto eventdto = new EventDto(startTime, endTime,name);
            return ResponseEntity.ok(eventdto);
        }
             else {
            return ResponseEntity.notFound().build();
             }
    }
    public class EventDto {
        private String name;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public EventDto(LocalDateTime startTime, LocalDateTime endTime,String name) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.name=name;
        }

        public LocalDateTime getStartTime() {
            return startTime;
        }

        public void setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
        }

        public LocalDateTime getEndTime() {
            return endTime;
        }

        public void setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
