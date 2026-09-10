-- 1. Очистка таблицы студентов от старых/измененных данных
DELETE FROM students;

-- Сброс автоинкремента, чтобы ID всегда начинались с 1
ALTER TABLE students AUTO_INCREMENT = 1;

-- 2. Заполнение таблиц 10 начальными студентами
INSERT INTO students (first_name, last_name, email) VALUES
('John', 'Doe', 'john.doe@gmail.com'),
('Jane', 'Smith', 'jane.smith@gmail.com'),
('Alex', 'Johnson', 'alex.j@gmail.com'),
('Emily', 'Davis', 'emily.davis@gmail.com'),
('Michael', 'Brown', 'michael.brown@gmail.com'),
('Sarah', 'Wilson', 'sarah.wilson@gmail.com'),
('David', 'Taylor', 'david.taylor@gmail.com'),
('Laura', 'Anderson', 'laura.anderson@gmail.com'),
('James', 'Thomas', 'james.thomas@gmail.com'),
('Emma', 'Jackson', 'emma.jackson@gmail.com');