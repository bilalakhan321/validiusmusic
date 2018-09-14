insert into artist (name) values ('Muse');
insert into artist (name) values ('Duran Duran');
INSERT INTO artist (name) values ('Van Hansen');


--INSERT INTO album (name, year_released, artist_id) values ('First Album of Van Hansen', 1995, 1);

insert into album(name, year_released, artist_id) values ('Drones', 2015, 1);
insert into album(name, year_released, artist_id) values ('Origin of Symmetry', 2001, 1);
insert into album(name, year_released, artist_id) values ('Rio', 1982, 2);
insert into album(name, year_released, artist_id) values ('1984', 1984, 3);

--INSERT INTO song (name, track, album_id) values ('Mercy', 1, 1);

--Songs----------------------------------------------------------------------
--Muse-Drones
insert into song(name, track, album_id) values('Dead Inside', 1, 1);
insert into song(name, track, album_id) values('Drill Sargeant', 2, 1);
insert into song(name, track, album_id) values('Psycho', 3, 1);
insert into song(name, track, album_id) values('Mercy', 4, 1);
insert into song(name, track, album_id) values('Reapers', 5, 1);
insert into song(name, track, album_id) values('The Handler', 6, 1);
insert into song(name, track, album_id) values('JFK', 7, 1);
insert into song(name, track, album_id) values('Defector', 8, 1);
insert into song(name, track, album_id) values('Revolt', 9, 1);
insert into song(name, track, album_id) values('Aftermath', 10, 1);
insert into song(name, track, album_id) values('The Globalist', 11, 1);
insert into song(name, track, album_id) values('Drones', 12, 1);
--Muse-Absolution
insert into song(name, track, album_id) values('Intro', 1, 2);
insert into song(name, track, album_id) values('Apocolypse Please', 2, 2);
insert into song(name, track, album_id) values('Time is Running Out', 3, 2);
insert into song(name, track, album_id) values('Sing for Absolution', 4, 2);
insert into song(name, track, album_id) values('Stokholm Syndrome', 5, 2);
insert into song(name, track, album_id) values('Falling Away With you', 6, 2);
insert into song(name, track, album_id) values('Interlude', 7, 2);
insert into song(name, track, album_id) values('Hysteria', 8, 2);
insert into song(name, track, album_id) values('Blackout', 9, 2);
insert into song(name, track, album_id) values('Butterflies and Hurricanes', 10, 2);
insert into song(name, track, album_id) values('The Small Print', 11, 2);
insert into song(name, track, album_id) values('Endlessly', 12, 2);
insert into song(name, track, album_id) values('Thoughts of a Dying Athiest', 13, 2);
insert into song(name, track, album_id) values('Ruled by Secrecy', 14, 2);
insert into song(name, track, album_id) values('Fury', 15, 2);
--Duran Duran-Rio
insert into song(name, track, album_id) values('Rio', 1, 3);
insert into song(name, track, album_id) values('My Own Way', 2, 3);
insert into song(name, track, album_id) values('Lonely in Your Nightmare', 3, 3);
insert into song(name, track, album_id) values('Hungry Like the Wolf', 4, 3);
insert into song(name, track, album_id) values('Hold Back the Rain', 5, 3);
insert into song(name, track, album_id) values('New Religion', 6, 3);
insert into song(name, track, album_id) values('Last Chance on the Stairway', 7, 3);
insert into song(name, track, album_id) values('Save a Prayer', 8, 3);
insert into song(name, track, album_id) values('The Chauffeur', 9, 3);
--Van Halen-1984
insert into song(name, track, album_id) values('1984', 1, 4);
insert into song(name, track, album_id) values('Jump', 2, 4);
insert into song(name, track, album_id) values('Panama', 3, 4);
insert into song(name, track, album_id) values('Top Jimmy', 4, 4);
insert into song(name, track, album_id) values('Drop Dead Legs', 5, 4);
insert into song(name, track, album_id) values('Hot for Teacher', 6, 4);
insert into song(name, track, album_id) values('Ill Wait', 7, 4);
insert into song(name, track, album_id) values('Girl Gone Bad', 8, 4);
insert into song(name, track, album_id) values('House of Pain', 9, 4);