-- liquibase formatted sql

-- changeset eastnetic:1684474126-1
INSERT INTO todos (created_at, updated_at, "name", description, due_date, user_id, status, is_important)
VALUES (CURRENT_DATE, CURRENT_DATE, 'Complete the frontend for Todo app',
        'Implement the remaining UI components and connect them to the backend API', CURRENT_DATE,
        (SELECT id FROM users WHERE email = 'john@gmail.com'), 102, TRUE),
       (CURRENT_DATE, CURRENT_DATE, 'Write documentation for Todo app',
        'Create user guide and developer documentation for the Todo app', CURRENT_DATE,
        (SELECT id FROM users WHERE email = 'john@gmail.com'), 101, TRUE),
       (CURRENT_DATE, CURRENT_DATE, 'Fix bugs in Todo app backend',
        'Resolve issues with the API endpoints and database queries', CURRENT_DATE,
        (SELECT id FROM users WHERE email = 'john@gmail.com'), 102, FALSE),
       (CURRENT_DATE, CURRENT_DATE, 'Design new logo for Todo app',
        'Create a new logo that reflects the brand and values of the Todo app', CURRENT_DATE,
        (SELECT id FROM users WHERE email = 'john@gmail.com'), 101, FALSE),
       (CURRENT_DATE, CURRENT_DATE, 'Add search functionality to Todo app',
        'Implement a search feature that allows users to find specific tasks', CURRENT_DATE,
        (SELECT id FROM users WHERE email = 'john@gmail.com'), 102, TRUE);

-- changeset eastnetic:1684474126-2
INSERT INTO todos (created_at, updated_at, "name", description, due_date, user_id, status, is_important)
VALUES ('2023-05-23 10:00:00', '2023-05-23 10:00:00', 'Buy groceries for the week',
        'Remember to buy milk, bread, vegetables, and meat.', '2023-05-25',
        (SELECT id FROM users WHERE email = 'alice@gmail.com'), 101, FALSE),
       ('2023-05-23 14:30:00', '2023-05-23 14:30:00', 'Schedule dentist appointment',
        'Call the dentist office and schedule a check-up appointment.', '2023-06-05',
        (SELECT id FROM users WHERE email = 'alice@gmail.com'), 101, FALSE),
       ('2023-05-24 11:45:00', '2023-05-24 11:45:00', 'Research new workout routine',
        'Spend some time researching and planning a new workout routine.', '2023-05-31',
        (SELECT id FROM users WHERE email = 'alice@gmail.com'), 101, TRUE),
       ('2023-05-25 16:00:00', '2023-05-25 16:00:00', 'Clean the house',
        'Spend the weekend cleaning and organizing the house.', '2023-05-29',
        (SELECT id FROM users WHERE email = 'alice@gmail.com'), 101, FALSE),
       ('2023-05-26 13:15:00', '2023-05-26 13:15:00', 'Plan vacation itinerary',
        'Spend some time researching and planning activities for the upcoming vacation.', '2023-06-15',
        (SELECT id FROM users WHERE email = 'alice@gmail.com'), 101, TRUE);

-- changeset eastnetic:1684474126-3
INSERT INTO todos (created_at, updated_at, "name", description, due_date, user_id, status, is_important)
VALUES ('2023-05-27 09:00:00', '2023-05-27 09:00:00', 'Complete the sales report',
        'Compile and analyze data for the monthly sales report.', '2023-06-10',
        (SELECT id FROM users WHERE email = 'mark@gmail.com'), 101, TRUE),
       ('2023-05-28 14:30:00', '2023-05-28 14:30:00', 'Attend team meeting',
        'Attend the weekly team meeting to discuss project updates and goals.', '2023-06-01',
        (SELECT id FROM users WHERE email = 'mark@gmail.com'), 101, FALSE),
       ('2023-05-29 11:45:00', '2023-05-29 11:45:00', 'Follow up with clients',
        'Follow up with clients to ensure their needs are being met and address any concerns.', '2023-06-03',
        (SELECT id FROM users WHERE email = 'mark@gmail.com'), 101, TRUE),
       ('2023-05-30 16:00:00', '2023-05-30 16:00:00', 'Research new marketing strategies',
        'Spend some time researching and planning new marketing strategies to increase sales and attract new customers.',
        '2023-06-15', (SELECT id FROM users WHERE email = 'mark@gmail.com'), 101, FALSE),
       ('2023-05-31 13:15:00', '2023-05-31 13:15:00', 'Attend training session',
        'Attend a training session to improve skills and knowledge in a specific area.', '2023-06-07',
        (SELECT id FROM users WHERE email = 'mark@gmail.com'), 101, TRUE);
