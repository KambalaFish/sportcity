INSERT INTO `sportcity`.`sportsman` (`id`, `name`, `club_name`) VALUES ('0', 'Vashenko Ivan Vitalievich', 'Arsenal');
INSERT INTO `sportcity`.`sportsman` (`id`, `name`, `club_name`) VALUES ('1', 'Leonov Inokenti Vyachislavovich', 'Spartak');
INSERT INTO `sportcity`.`sportsman` (`id`, `name`, `club_name`) VALUES ('2', 'Eskerov Elgar Ivanovich', 'CSKA');
INSERT INTO `sportcity`.`sportsman` (`id`, `name`, `club_name`) VALUES ('3', 'Tivikov Konstantin Pavlovich', 'Lokomotiv');
INSERT INTO `sportcity`.`sportsman` (`id`, `name`, `club_name`) VALUES ('4', 'Sementin Semen Ilich', 'Chelsea');
INSERT INTO `sportcity`.`sportsman` (`id`, `name`, `club_name`) VALUES ('5', 'Savvateev Andrey Andreich', 'Dynamo');
INSERT INTO `sportcity`.`sportsman` (`id`, `name`, `club_name`) VALUES ('6', 'Abuov Bek Bekich', 'Dynamo');
INSERT INTO `sportcity`.`sportsman` (`id`, `name`, `club_name`) VALUES ('7', 'Shahurdin Konstantin Sixovich', 'Dynamo');

INSERT INTO `sportcity`.`coach` (`id`, `name`, `sport`) VALUES ('0', 'Chizhov Nikita Michailovich', 'football');
INSERT INTO `sportcity`.`coach` (`id`, `name`, `sport`) VALUES ('1', 'Lebedev Yan Borisovich', 'tennis');
INSERT INTO `sportcity`.`coach` (`id`, `name`, `sport`) VALUES ('2', 'Kapanadze Georgy Abramovich', 'hockey');
INSERT INTO `sportcity`.`coach` (`id`, `name`, `sport`) VALUES ('3', 'Ganiev Rinat Ramilievich', 'volleyball');
INSERT INTO `sportcity`.`coach` (`id`, `name`, `sport`) VALUES ('4', 'Besmertny Varlam Isakovich', 'tennis');

INSERT INTO `sportcity`.`mentoring` (`sportsman_id`, `coach_id`) VALUES ('0', '0');
INSERT INTO `sportcity`.`mentoring` (`sportsman_id`, `coach_id`) VALUES ('1', '1');
INSERT INTO `sportcity`.`mentoring` (`sportsman_id`, `coach_id`) VALUES ('2', '2');
INSERT INTO `sportcity`.`mentoring` (`sportsman_id`, `coach_id`) VALUES ('3', '3');
INSERT INTO `sportcity`.`mentoring` (`sportsman_id`, `coach_id`) VALUES ('4', '4');
INSERT INTO `sportcity`.`mentoring` (`sportsman_id`, `coach_id`) VALUES ('5', '0');
INSERT INTO `sportcity`.`mentoring` (`sportsman_id`, `coach_id`) VALUES ('2', '1');

INSERT INTO `sportcity`.`abilities` (`id`, `sportsman_id`, `category`, `sport`) VALUES ('0', '0', '2', 'football');
INSERT INTO `sportcity`.`abilities` (`id`, `sportsman_id`, `category`, `sport`) VALUES ('1', '1', '5', 'tennis');
INSERT INTO `sportcity`.`abilities` (`id`, `sportsman_id`, `category`, `sport`) VALUES ('2', '2', '6', 'hockey');
INSERT INTO `sportcity`.`abilities` (`id`, `sportsman_id`, `category`, `sport`) VALUES ('3', '3', '3', 'volleyball');
INSERT INTO `sportcity`.`abilities` (`id`, `sportsman_id`, `category`, `sport`) VALUES ('4', '4', '4', 'tennis');
INSERT INTO `sportcity`.`abilities` (`id`, `sportsman_id`, `category`, `sport`) VALUES ('5', '2', '1', 'tennis');
INSERT INTO `sportcity`.`abilities` (`id`, `sportsman_id`, `category`, `sport`) VALUES ('6', '5', '3', 'football');
INSERT INTO `sportcity`.`abilities` (`id`, `sportsman_id`, `category`, `sport`) VALUES ('7', '6', '6', 'football');
INSERT INTO `sportcity`.`abilities` (`id`, `sportsman_id`, `category`, `sport`) VALUES ('8', '7', '8', 'football');

INSERT INTO `sportcity`.`competition` (`id`, `c_date`, `sport`) VALUES ('0', '2020-01-01', 'football');
INSERT INTO `sportcity`.`competition` (`id`, `c_date`, `sport`) VALUES ('1', '2020-01-02', 'tennis');
INSERT INTO `sportcity`.`competition` (`id`, `c_date`, `sport`) VALUES ('2', '2020-01-03', 'hockey');
INSERT INTO `sportcity`.`competition` (`id`, `c_date`, `sport`) VALUES ('3', '2020-01-04', 'volleyball');
INSERT INTO `sportcity`.`competition` (`id`, `c_date`, `sport`) VALUES ('4', '2020-01-05', 'tennis');

INSERT INTO `sportcity`.`participation` (`sportsman_id`, `competition_id`) VALUES ('0', '0');
INSERT INTO `sportcity`.`participation` (`sportsman_id`, `competition_id`) VALUES ('1', '1');
INSERT INTO `sportcity`.`participation` (`sportsman_id`, `competition_id`) VALUES ('2', '2');
INSERT INTO `sportcity`.`participation` (`sportsman_id`, `competition_id`) VALUES ('3', '3');
INSERT INTO `sportcity`.`participation` (`sportsman_id`, `competition_id`) VALUES ('4', '4');
INSERT INTO `sportcity`.`participation` (`sportsman_id`, `competition_id`) VALUES ('1', '4');
INSERT INTO `sportcity`.`participation` (`sportsman_id`, `competition_id`) VALUES ('4', '1');
INSERT INTO `sportcity`.`participation` (`sportsman_id`, `competition_id`) VALUES ('5', '0');
INSERT INTO `sportcity`.`participation` (`sportsman_id`, `competition_id`) VALUES ('6', '0');

INSERT INTO `sportcity`.`organizer` (`id`, `name`) VALUES ('0', 'Mazhugov Dmitry Maksimovich');
INSERT INTO `sportcity`.`organizer` (`id`, `name`) VALUES ('1', 'Dolgov Valeriy Stepanovich');
INSERT INTO `sportcity`.`organizer` (`id`, `name`) VALUES ('2', 'Bashkatov Andrey Andreevich');
INSERT INTO `sportcity`.`organizer` (`id`, `name`) VALUES ('3', 'Laburtsov Alexandr Vasilievich');
INSERT INTO `sportcity`.`organizer` (`id`, `name`) VALUES ('4', 'Zlagaduhin Vlad Petrovich');

INSERT INTO `sportcity`.`arrangement` (`competition_id`, `organizer_id`) VALUES ('0', '0');
INSERT INTO `sportcity`.`arrangement` (`competition_id`, `organizer_id`) VALUES ('1', '1');
INSERT INTO `sportcity`.`arrangement` (`competition_id`, `organizer_id`) VALUES ('2', '2');
INSERT INTO `sportcity`.`arrangement` (`competition_id`, `organizer_id`) VALUES ('3', '3');
INSERT INTO `sportcity`.`arrangement` (`competition_id`, `organizer_id`) VALUES ('4', '4');

INSERT INTO `sportcity`.`sport_facilities` (`id`) VALUES ('0');
INSERT INTO `sportcity`.`sport_facilities` (`id`) VALUES ('1');
INSERT INTO `sportcity`.`sport_facilities` (`id`) VALUES ('2');
INSERT INTO `sportcity`.`sport_facilities` (`id`) VALUES ('3');
INSERT INTO `sportcity`.`sport_facilities` (`id`) VALUES ('4');

INSERT INTO `sportcity`.`court` (`id`, `coverage_type`) VALUES ('0', 'clay');
INSERT INTO `sportcity`.`court` (`id`, `coverage_type`) VALUES ('1', 'grass');
INSERT INTO `sportcity`.`stadium` (`id`, `capacity`) VALUES ('2', '50000');
INSERT INTO `sportcity`.`ice_arena` (`id`, `square`) VALUES ('3', '500');
INSERT INTO `sportcity`.`volleyball_arena` (`id`, `net_height`, `net_width`) VALUES ('4', '1', '7');

INSERT INTO `sportcity`.`location` (`competition_id`, `sport_facilities_id`) VALUES ('1', '0');
INSERT INTO `sportcity`.`location` (`competition_id`, `sport_facilities_id`) VALUES ('4', '1');
INSERT INTO `sportcity`.`location` (`competition_id`, `sport_facilities_id`) VALUES ('0', '2');
INSERT INTO `sportcity`.`location` (`competition_id`, `sport_facilities_id`) VALUES ('2', '3');
INSERT INTO `sportcity`.`location` (`competition_id`, `sport_facilities_id`) VALUES ('3', '4');