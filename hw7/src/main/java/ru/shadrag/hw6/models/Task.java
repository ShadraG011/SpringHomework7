package ru.shadrag.hw6.models;

import jakarta.persistence.*;
import ru.shadrag.hw6.components.Status;

import java.util.Date;


/**
 * ID (автоинкрементное)
 * Описание (не может быть пустым)
 * Статус (одно из значений: "не начата", "в процессе", "завершена")
 * Дата создания (автоматически устанавливается при создании задачи)
 */
@Entity
@Table(name = "tasks")
public class Task {

    //region КОНСТРУКТОРЫ
    public Task() {
        this.status = Status.NOT_START;
        this.date = new Date();
    }
    //endregion

    //region ГЕТТЕРЫ И СЕТТЕРЫ
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        if (user == null) {
            return "Не назначен";
        }
        return user.getUsername();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //endregion

    //region ПОЛЯ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status")
    private Status status;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    //endregion
}
