package com.example.moviecatalog.utils

import com.example.moviecatalog.R
import com.example.moviecatalog.data.MovieEntity
import com.example.moviecatalog.data.TvEntity

object DataDummy{

    fun generateDummyMovie():List<MovieEntity>{
        val movies = ArrayList<MovieEntity>()
        val posterMovie = arrayOf(
            R.drawable.movie_show_poster_alita,
            R.drawable.movie_show_poster_aquaman,
            R.drawable.movie_show_poster_a_start_is_born,
            R.drawable.movie_show_poster_bohemian,
            R.drawable.movie_show_poster_cold_pursuit,
            R.drawable.movie_show_poster_creed2,
            R.drawable.movie_show_poster_crimes,
            R.drawable.movie_show_poster_glass,
            R.drawable.movie_show_poster_how_to_train,
            R.drawable.movie_show_poster_infinity_war
        )

        val titleMovies = arrayOf(
            "Alita: Battle Angel",
            "Aquaman",
            "A Star is Born",
            "Bohemian Rhapsody",
            "Cold Pursuit",
            "Creed2",
            "Fantastic Beast: The Crimes Of Grindelwald",
            "Glass",
            "How to Train Your Dragon: The Hidden World",
            "Avengers: Infinity War"
        )

        val descriptionMovies = arrayOf(
            """
               When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido,
            a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.
            """.trimIndent(),
            """
               Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm.
            With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry,
            Orm\'s half-human, half-Atlantean brother and true heir to the throne.    
            """.trimIndent(),
            """
               Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally.
            She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally\'s career takes off,
            the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.
            """.trimIndent(),
            """
               Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock \'n\'
            roll band Queen in 1970. Hit songs become instant classics. When Mercury\'s increasingly wild lifestyle starts to spiral out of control,
            Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.
            """.trimIndent(),
            """
               The quiet family life of Nels Coxman, a snowplow driver, is upended after his son\'s murder. Nels begins a vengeful hunt for Viking,
            the drug lord he holds responsible for the killing, eliminating Viking\'s associates one by one. As Nels draws closer to Viking,
            his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.
            """.trimIndent(),
            """
               Between personal obligations and training for his next big fight against an opponent with ties to his family\'s past,
            Adonis Creed is up against the challenge of his life.
            """.trimIndent(),
            """
              Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings.
            The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However,
            Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander,
            who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family,
            in an increasingly divided wizarding world.
            """.trimIndent(),
            """
               In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb,
            a disturbed man who has twenty-four personalities. Meanwhile,
            the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.
            """.trimIndent(),
            """
               As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless discovery of an untamed, elusive mate draws the Night Fury away.
            When danger mounts at home and Hiccup\’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.
            """.trimIndent(),
            """
               As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle,
            a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones,
            artifacts of unimaginable power, and use them to inflict his twisted will on all of reality.
            Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.
            """.trimIndent()
        )

        val dateRelease = arrayOf(
            "14/02/2019",
            "21/12/2018",
            "05/10/2018",
            "02/11/2018",
            "08/02/2019",
            "21/11/2018",
            "16/11/2018",
            "18/01/2019",
            "22/02/2019",
            "27/04/2018"
        )

        val durationMovie = arrayOf(
            "2h 2m", "2h 24m" , "2h 16m" , "2h 15m" , "1h 59m" , "2h 10m" , "2m", "2h 9m ", "1h 44m" , "2h 29m"
        )

        val directorMovie = arrayOf(
            "Robert Rodriguez",
            "James Wan",
            "Bradley Cooper",
            "Bryan Singer",
            "Hans Petter Moland",
            "Steven Caple Jr.",
            "David Yates",
            "M. Night Shyamalan",
            "Dean DeBlois",
            "Joe Russo"
        )

        val screenPlay = arrayOf(
            "James Cameron",
            "Will Beall",
            "Will Fetters",
            "Anthony McCarten",
            "Frank Baldwin",
            "Juel Taylor",
            "J.K. Rowling",
            "M. Night Shyamalan",
            "Dean DeBlois",
            "Stephen McFeely"
        )

        val yearsRelease = arrayOf(
            "2019", "2018", "2018", "2018", "2019", "2018", "2018", "2019", "2019", "2018"
        )

        for (position in titleMovies.indices){
            val itemMovie = MovieEntity(
                titleMovies[position],
                descriptionMovies[position],
                dateRelease[position],
                posterMovie[position],
                durationMovie[position],
                directorMovie[position],
                screenPlay[position],
                yearsRelease[position]
            )
            movies.add(itemMovie)
        }
        return movies
    }

    fun generateDummyTV():List<TvEntity>{
        val tv = ArrayList<TvEntity>()
        val poster = arrayOf(
            R.drawable.tv_show_poster_arrow,
            R.drawable.tv_show_poster_doom_patrol,
            R.drawable.tv_show_poster_dragon_ball,
            R.drawable.tv_show_poster_fairytail,
            R.drawable.tv_show_poster_family_guy,
            R.drawable.tv_show_poster_flash,
            R.drawable.tv_show_poster_god,
            R.drawable.tv_show_poster_gotham,
            R.drawable.tv_show_poster_naruto_shipudden,
            R.drawable.tv_show_poster_grey_anatomy,
        )

        val titleTv = arrayOf(
            "The Arrow", "Doom Patrol", "Dragon Ball", "Fairy Tail",
            "Family Guy", "flash", "Game of Thrones", "Gotham",
            "Naruto Shipudden", "Grey\'s Anatomy "
        )

        val description = arrayOf(
            """
                Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea.
            He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.
            """.trimIndent(),
            """
                The Doom Patrol\’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured.
            Traumatized and downtrodden, the team found purpose through The Chief,
            who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.
            """.trimIndent(),
            """
                Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku.
            Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed.
            Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku\'s home.
            Together, they set off to find all seven and to grant her wish.
            """.trimIndent(),
            """
                Natsu Dragneel and his friends travel to the island Kingdom of Stella, where they will reveal dark secrets,
            fight the new enemies and once again save the world from destruction.
            """.trimIndent(),
            """
                Sick, twisted, politically incorrect and Freakin\' Sweet animated series featuring the adventures of the dysfunctional Griffin family.
            Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world),
            Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he\'s not very bright but has a passion for movies).
            The final member of the family is Brian - a talking dog and much more than a pet,
            he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.
            """.trimIndent(),
            """
                After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma.
            Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel.
            Though initially excited by his newfound powers,
            Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator
            explosion — and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent.
            For now, only a few close friends and associates know that Barry is literally the fastest man alive,
            but it won\'t be long before the world learns what Barry Allen has become The Flash.
            """.trimIndent(),
            """
                Seven noble families fight for control of the mythical land of Westeros.
            Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north.
            Amidst the war, a neglected military order of misfits, the Night\'s Watch, is all that stands between the realms of men and icy horrors beyond.
            """.trimIndent(),
            """
                Everyone knows the name Commissioner Gordon. He is one of the crime world\'s greatest foes,
            a man whose reputation is synonymous with law and order.
            But what is known of Gordon\'s story and his rise from rookie detective to Police Commissioner?
            What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City,
            the spawning ground of the world\'s most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman,
            The Penguin, The Riddler, Two-Face and The Joker?
            """.trimIndent(),
            """
                emons that once almost destroyed the world, are revived by someone.
            To prevent the world from being destroyed, the demon has to be sealed and the only one who can do it is the shrine maiden Shion from the country of demons,
            who has two powers; one is sealing demons and the other is predicting the deaths of humans. This time Naruto\'s mission is to guard Shion,
            but she predicts Naruto\'s death. The only way to escape it, is to get away from Shion, which would leave her unguarded, then the demon,
            whose only goal is to kill Shion will do so, thus meaning the end of the world. Naruto decides to challenge this "prediction of death.
            """.trimIndent(),
            """
                Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.
            """.trimIndent()
        )

        val broadcastDate = arrayOf(
            "08/10/2019", "25/06/2020", "26/02/1986", "06/05/2018 (last episode)",
            "27/09/2020", "20/09/1990", "14/04/2019", "03/01/2019",
            "27/05/2015 (last episode)", "12/11/2020"
        )

        val duration = arrayOf(
            "42m", "49m", "25m", "22m", "45m", "1h", "43m", "25m", "43m","43m"
        )

        val kreator = arrayOf(
            "Greg Berlanti", "Jeremy Carver", "Akira Toriyama",
            "Hiro Mashima", "Seth MacFarlane", "Danny Bilson",
            "David Benioff", "Bruno Heller", "Masashi Kishimoto",
            "Shonda Rhimes"
        )

        val writerTv = arrayOf(
            "unknown", "unknown", "Hiro Mashima", "Seth MacFarlane",
            "unknown", "unknown", "unknown", "unknown",
            "Masashi Kishimoto", "unknown"
        )

        val yearPublish = arrayOf(
            "2012", "2019", "1986", "2009", "1999", "1990", "2011", "2014", "2007", "2005"
        )

        for (position in titleTv.indices){
            val itemTV = TvEntity(
                titleTv[position],
                description[position],
                broadcastDate[position],
                poster[position],
                duration[position],
                writerTv[position],
                kreator[position],
                yearPublish[position]
            )
            tv.add(itemTV)
        }
        return tv
    }
}