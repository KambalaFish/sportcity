INSERT INTO `sportcity`.`club` (`name`) VALUES ('Arsenal');
INSERT INTO `sportcity`.`club` (`name`) VALUES ('Spartak');
INSERT INTO `sportcity`.`club` (`name`) VALUES ('CSKA');
INSERT INTO `sportcity`.`club` (`name`) VALUES ('Lokomotiv');
INSERT INTO `sportcity`.`club` (`name`) VALUES ('Chelsea');
INSERT INTO `sportcity`.`club` (`name`) VALUES ('Dynamo');


INSERT INTO `sportcity`.`sportsman` (`name`, `club_id`) VALUES ('Vashenko Ivan Vitalievich', '1');
INSERT INTO `sportcity`.`sportsman` (`name`, `club_id`) VALUES ('Leonov Inokenti Vyachislavovich', '2');
INSERT INTO `sportcity`.`sportsman` (`name`, `club_id`) VALUES ('Eskerov Elgar Ivanovich', '3');
INSERT INTO `sportcity`.`sportsman` (`name`, `club_id`) VALUES ('Tivikov Konstantin Pavlovich', '4');
INSERT INTO `sportcity`.`sportsman` (`name`, `club_id`) VALUES ('Sementin Semen Ilich', '5');
INSERT INTO `sportcity`.`sportsman` (`name`, `club_id`) VALUES ('Savvateev Andrey Andreich', '6');
INSERT INTO `sportcity`.`sportsman` (`name`, `club_id`) VALUES ('Abuov Bek Bekich', '6');
INSERT INTO `sportcity`.`sportsman` (`name`, `club_id`) VALUES ('Shahurdin Konstantin Sixovich', '6');
INSERT INTO `sportcity`.`sportsman` (`name`, `club_id`) VALUES ('Goncharov Nikita Grigorievich', '3');
INSERT INTO `sportcity`.`sportsman` (`name`, `club_id`) VALUES ('Tumanov Nikolai Markovich', '3');

INSERT INTO `sportcity`.`coach` (`name`, `sport`) VALUES ('Chizhov Nikita Michailovich', 'football');
INSERT INTO `sportcity`.`coach` (`name`, `sport`) VALUES ('Lebedev Yan Borisovich', 'tennis');
INSERT INTO `sportcity`.`coach` (`name`, `sport`) VALUES ('Kapanadze Georgy Abramovich', 'hockey');
INSERT INTO `sportcity`.`coach` (`name`, `sport`) VALUES ('Ganiev Rinat Ramilievich', 'volleyball');
INSERT INTO `sportcity`.`coach` (`name`, `sport`) VALUES ('Besmertny Varlam Isakovich', 'tennis');
INSERT INTO `sportcity`.`coach` (`name`, `sport`) VALUES ('Kapanadze Elisabeth Andreevna', 'figureSkating');
INSERT INTO `sportcity`.`coach` (`name`, `sport`) VALUES ('Salotkin Igor Valerievich', 'athletics');

INSERT INTO `sportcity`.`mentoring` (`sportsman_id`, `coach_id`) VALUES ('1', '1');
INSERT INTO `sportcity`.`mentoring` (`sportsman_id`, `coach_id`) VALUES ('2', '2');
INSERT INTO `sportcity`.`mentoring` (`sportsman_id`, `coach_id`) VALUES ('3', '3');
INSERT INTO `sportcity`.`mentoring` (`sportsman_id`, `coach_id`) VALUES ('4', '4');
INSERT INTO `sportcity`.`mentoring` (`sportsman_id`, `coach_id`) VALUES ('5', '5');
INSERT INTO `sportcity`.`mentoring` (`sportsman_id`, `coach_id`) VALUES ('6', '1');
INSERT INTO `sportcity`.`mentoring` (`sportsman_id`, `coach_id`) VALUES ('3', '2');
INSERT INTO `sportcity`.`mentoring` (`sportsman_id`, `coach_id`) VALUES ('9', '6');
INSERT INTO `sportcity`.`mentoring` (`sportsman_id`, `coach_id`) VALUES ('10', '7');

INSERT INTO `sportcity`.`abilities` (`sportsman_id`, `category`, `sport`) VALUES ('1', '2', 'football');
INSERT INTO `sportcity`.`abilities` (`sportsman_id`, `category`, `sport`) VALUES ('2', '5', 'tennis');
INSERT INTO `sportcity`.`abilities` (`sportsman_id`, `category`, `sport`) VALUES ('3', '6', 'hockey');
INSERT INTO `sportcity`.`abilities` (`sportsman_id`, `category`, `sport`) VALUES ('4', '3', 'volleyball');
INSERT INTO `sportcity`.`abilities` (`sportsman_id`, `category`, `sport`) VALUES ('5', '4', 'tennis');
INSERT INTO `sportcity`.`abilities` (`sportsman_id`, `category`, `sport`) VALUES ('3', '1', 'tennis');
INSERT INTO `sportcity`.`abilities` (`sportsman_id`, `category`, `sport`) VALUES ('6', '3', 'football');
INSERT INTO `sportcity`.`abilities` (`sportsman_id`, `category`, `sport`) VALUES ('7', '6', 'football');
INSERT INTO `sportcity`.`abilities` (`sportsman_id`, `category`, `sport`) VALUES ('8', '8', 'football');
INSERT INTO `sportcity`.`abilities` (`sportsman_id`, `category`, `sport`) VALUES ('9', '2', 'figureSkating');
INSERT INTO `sportcity`.`abilities` (`sportsman_id`, `category`, `sport`) VALUES ('10', '4', 'athletics');

INSERT INTO `sportcity`.`competition` (`name`, `beginning_date`, `finish_date`, `sport`) VALUES ('Kozhany myach', '2020-01-01', '2020-02-01', 'football');
INSERT INTO `sportcity`.`competition` (`name`, `beginning_date`, `finish_date`, `sport`) VALUES ('wimbledon', '2020-01-02', '2020-01-25', 'tennis');
INSERT INTO `sportcity`.`competition` (`name`, `beginning_date`, `finish_date`, `sport`) VALUES ('NHL', '2020-01-03', '2020-05-06', 'hockey');
INSERT INTO `sportcity`.`competition` (`name`, `beginning_date`, `finish_date`, `sport`) VALUES ('Euro League', '2020-01-04', '2020-03-04', 'volleyball');
INSERT INTO `sportcity`.`competition` (`name`, `beginning_date`, `finish_date`, `sport`) VALUES ('US open','2020-02-05', '2020-04-06', 'tennis');
INSERT INTO `sportcity`.`competition` (`name`, `beginning_date`, `finish_date`, `sport`) VALUES ('Cup of Europe','2020-03-06', '2020-05-06', 'figureSkating');
INSERT INTO `sportcity`.`competition` (`name`, `beginning_date`, `finish_date`, `sport`) VALUES ('Cup of Russia','2020-03-06', '2020-05-06', 'athletics');

INSERT INTO `sportcity`.`participation` (`sportsman_id`, `competition_id`) VALUES ('1', '1');
INSERT INTO `sportcity`.`participation` (`sportsman_id`, `competition_id`) VALUES ('2', '2');
INSERT INTO `sportcity`.`participation` (`sportsman_id`, `competition_id`) VALUES ('3', '3');
INSERT INTO `sportcity`.`participation` (`sportsman_id`, `competition_id`) VALUES ('4', '4');
INSERT INTO `sportcity`.`participation` (`sportsman_id`, `competition_id`) VALUES ('5', '5');
INSERT INTO `sportcity`.`participation` (`sportsman_id`, `competition_id`) VALUES ('2', '5');
INSERT INTO `sportcity`.`participation` (`sportsman_id`, `competition_id`) VALUES ('5', '2');
INSERT INTO `sportcity`.`participation` (`sportsman_id`, `competition_id`) VALUES ('6', '1');
INSERT INTO `sportcity`.`participation` (`sportsman_id`, `competition_id`) VALUES ('7', '1');
INSERT INTO `sportcity`.`participation` (`sportsman_id`, `competition_id`) VALUES ('9', '6');
INSERT INTO `sportcity`.`participation` (`sportsman_id`, `competition_id`) VALUES ('10', '7');

INSERT INTO `sportcity`.`organizer` (`name`) VALUES ('Mazhugov Dmitry Maksimovich');
INSERT INTO `sportcity`.`organizer` (`name`) VALUES ('Dolgov Valeriy Stepanovich');
INSERT INTO `sportcity`.`organizer` (`name`) VALUES ('Bashkatov Andrey Andreevich');
INSERT INTO `sportcity`.`organizer` (`name`) VALUES ('Laburtsov Alexandr Vasilievich');
INSERT INTO `sportcity`.`organizer` (`name`) VALUES ('Zlagaduhin Vlad Petrovich');

INSERT INTO `sportcity`.`arrangement` (`competition_id`, `organizer_id`) VALUES ('1', '1');
INSERT INTO `sportcity`.`arrangement` (`competition_id`, `organizer_id`) VALUES ('2', '2');
INSERT INTO `sportcity`.`arrangement` (`competition_id`, `organizer_id`) VALUES ('3', '3');
INSERT INTO `sportcity`.`arrangement` (`competition_id`, `organizer_id`) VALUES ('4', '4');
INSERT INTO `sportcity`.`arrangement` (`competition_id`, `organizer_id`) VALUES ('5', '5');
INSERT INTO `sportcity`.`arrangement` (`competition_id`, `organizer_id`) VALUES ('6', '3');
INSERT INTO `sportcity`.`arrangement` (`competition_id`, `organizer_id`) VALUES ('7', '4');

INSERT INTO `sportcity`.`sport_facilities` (`id`) VALUES ('1');
INSERT INTO `sportcity`.`sport_facilities` (`id`) VALUES ('2');
INSERT INTO `sportcity`.`sport_facilities` (`id`) VALUES ('3');
INSERT INTO `sportcity`.`sport_facilities` (`id`) VALUES ('4');
INSERT INTO `sportcity`.`sport_facilities` (`id`) VALUES ('5');

INSERT INTO `sportcity`.`court` (`id`, `coverage_type`) VALUES ('1', 'clay');
INSERT INTO `sportcity`.`court` (`id`, `coverage_type`) VALUES ('2', 'grass');
INSERT INTO `sportcity`.`stadium` (`id`, `capacity`) VALUES ('3', '50000');
INSERT INTO `sportcity`.`ice_arena` (`id`, `square`) VALUES ('4', '500');
INSERT INTO `sportcity`.`volleyball_arena` (`id`, `net_height`, `net_width`) VALUES ('5', '1', '7');

INSERT INTO `sportcity`.`location` (`competition_id`, `sport_facilities_id`) VALUES ('2', '1');
INSERT INTO `sportcity`.`location` (`competition_id`, `sport_facilities_id`) VALUES ('5', '2');
INSERT INTO `sportcity`.`location` (`competition_id`, `sport_facilities_id`) VALUES ('1', '3');
INSERT INTO `sportcity`.`location` (`competition_id`, `sport_facilities_id`) VALUES ('3', '4');
INSERT INTO `sportcity`.`location` (`competition_id`, `sport_facilities_id`) VALUES ('4', '5');
INSERT INTO `sportcity`.`location` (`competition_id`, `sport_facilities_id`) VALUES ('6', '4');
INSERT INTO `sportcity`.`location` (`competition_id`, `sport_facilities_id`) VALUES ('7', '3');

INSERT INTO `sportcity`.`prizewinner` (`sportsman_id`, `competition_id`) VALUES ('3', '3');
INSERT INTO `sportcity`.`prizewinner` (`sportsman_id`, `competition_id`) VALUES ('2', '5');
INSERT INTO `sportcity`.`prizewinner` (`sportsman_id`, `competition_id`) VALUES ('5', '2');
INSERT INTO `sportcity`.`prizewinner` (`sportsman_id`, `competition_id`) VALUES ('6', '1');
INSERT INTO `sportcity`.`prizewinner` (`sportsman_id`, `competition_id`) VALUES ('4', '4');
INSERT INTO `sportcity`.`prizewinner` (`sportsman_id`, `competition_id`) VALUES ('9', '6');
INSERT INTO `sportcity`.`prizewinner` (`sportsman_id`, `competition_id`) VALUES ('10', '7');
