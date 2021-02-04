package com.example.moviecatalog.utils

import com.example.moviecatalog.data.source.local.*
import com.example.moviecatalog.data.source.remote.response.*

object DataDummy{

    fun generateDummyMovie():List<MovieEntitys>{
        val movies = ArrayList<MovieEntitys>()
        val posterMovie = arrayOf(
            "https://image.tmdb.org/t/p/w780//h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
            "https://image.tmdb.org/t/p/w780//ugZW8ocsrfgI95pnQ7wrmKDxIe.jpg",
            "https://image.tmdb.org/t/p/w780//betExZlgK0l7CZ9CsCBVcwO1OjL.jpg",
            "https://image.tmdb.org/t/p/w780//jlJ8nDhMhCYJuzOw3f52CP1W8MW.jpg",
            "https://image.tmdb.org/t/p/w780//7D430eqZj8y3oVkLFfsWXGRcpEG.jpg",
            "https://image.tmdb.org/t/p/w780//hddzYJtfYYeMDOQVcH58n8m1W3A.jpg",
            "https://image.tmdb.org/t/p/w780//elZ6JCzSEvFOq4gNjNeZsnRFsvj.jpg",
            "https://image.tmdb.org/t/p/w780//lQfdytwN7eh0tXWjIiMceFdBBvD.jpg",
            "https://image.tmdb.org/t/p/w780//riYInlsq2kf1AWoGm80JQW5dLKp.jpg",
            "https://image.tmdb.org/t/p/w780//9HT9982bzgN5on1sLRmc1GMn6ZC.jpg",
            "https://image.tmdb.org/t/p/w780//6agKYU5IQFpuDyUYPu39w7UCRrJ.jpg",
            "https://image.tmdb.org/t/p/w780//sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
            "https://image.tmdb.org/t/p/w780//aKx1ARwG55zZ0GpRvU2WrGrCG9o.jpg",
            "https://image.tmdb.org/t/p/w780//6CoRTJTmijhBLJTUNoVSUNxZMEI.jpg",
            "https://image.tmdb.org/t/p/w780//xrI4EnZWftpo1B7tTvlMUXVOikd.jpg",
            "https://image.tmdb.org/t/p/w780//qzA87Wf4jo1h8JMk9GilyIYvwsA.jpg",
            "https://image.tmdb.org/t/p/w780//kPzcvxBwt7kEISB9O4jJEuBn72t.jpg",
            "https://image.tmdb.org/t/p/w780//xqbQtMffXwa3oprse4jiHBMBvdW.jpg",
            "https://image.tmdb.org/t/p/w780//kiX7UYfOpYrMFSAGbI6j1pFkLzQ.jpg",
            "https://image.tmdb.org/t/p/w780//xqvX5A24dbIWaeYsMTxxKX5qOfz.jpg",
        )

        val title = arrayOf(
            "Demon Slayer: Kimetsu no Yaiba - The Movie: Mugen Train",
            "Hard Kill",
            "Roald Dahl's The Witches",
            "The SpongeBob Movie: Sponge on the Run",
            "2067",
            "Once Upon a Snowman",
            "Welcome to Sudden Death",
            "Over the Moon",
            "Enola Holmes",
            "Rogue City",
            "Borat Subsequent Moviefilm",
            "Peninsula",
            "Mulan",
            "Money Plane",
            "The New Mutants",
            "Ava",
            "We Bare Bears: The Movie",
            "Becky",
            "After We Collided",
            "American Pie Presents: Girls Rules",
        )

        val dateRelease = arrayOf(
            "2020-10-16",
            "2020-10-23",
            "2020-10-26",
            "2020-08-14",
            "2020-10-01",
            "2020-10-23",
            "2020-09-29",
            "2020-10-16",
            "2020-09-23",
            "2020-10-30",
            "2020-10-23",
            "2020-07-15",
            "2020-09-04",
            "2020-09-29",
            "2020-08-26",
            "2020-07-02",
            "2020-06-30",
            "2020-07-23",
            "2020-09-02",
            "2020-10-06"
        )

        val popularity = arrayOf(
            1380.001,
            1732.713,
            1661.695,
            1560.835,
            1286.086,
            1075.075,
            903.538,
            796.405,
            800.607,
            1158.648,
            681.016,
            744.918,
            657.875,
            645.159,
            672.384,
            582.577,
            570.995,
            526.037,
            772.708,
            488.34
        )

        val id = arrayOf(
            635302,
            724989,
            531219,
            400160,
            528085,
            741074,
            741067,
            560050,
            497582,
            671039,
            740985,
            581392,
            337401,
            694919,
            340102,
            539885,
            677638,
            601844,
            613504,
            660982
        )

        for (position in title.indices){
            val itemMovie = MovieEntitys(
                releaseDate = dateRelease[position],
                popularity = popularity[position],
                title = title[position],
                id = id[position],
                posterPath = posterMovie[position]
            )
            movies.add(itemMovie)
        }
        return movies
    }

    fun generateDummyTV():List<TvEntitys>{
        val tv = ArrayList<TvEntitys>()

        val poster = arrayOf(
            "https://image.tmdb.org/t/p/w780//6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
            "https://image.tmdb.org/t/p/w780//sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
            "https://image.tmdb.org/t/p/w780//wGFUewXPeMErCe2xnCmmLEiHOGh.jpg",
            "https://image.tmdb.org/t/p/w780//4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
            "https://image.tmdb.org/t/p/w780//mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
            "https://image.tmdb.org/t/p/w780//zU0htwkhNvBQdVSIKB9s6hgVeFK.jpg",
            "https://image.tmdb.org/t/p/w780//z31GxpVgDsFAF4paZR8PRsiP16D.jpg",
            "https://image.tmdb.org/t/p/w780//g6tIKGc3f1H5QMz1dcgCwADKpZ7.jpg",
            "https://image.tmdb.org/t/p/w780//clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
            "https://image.tmdb.org/t/p/w780//2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg",
            "https://image.tmdb.org/t/p/w780//wcaDIAG1QdXQLRaj4vC1EFdBT2.jpg",
            "https://image.tmdb.org/t/p/w780//scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg",
            "https://image.tmdb.org/t/p/w780//u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
            "https://image.tmdb.org/t/p/w780//aBkVgChtyyJaHyZh1gfd8DbzQon.jpg",
            "https://image.tmdb.org/t/p/w780//KoYWXbnYuS3b0GyQPkbuexlVK9.jpg",
            "https://image.tmdb.org/t/p/w780//4X7o1ssOEvp4BFLim1AZmPNcYbU.jpg",
            "https://image.tmdb.org/t/p/w780//qgjP2OrrX9gc6M270xdPnEmE9tC.jpg",
            "https://image.tmdb.org/t/p/w780//oogunE22HDTcTxFakKQbwqfw1qo.jpg",
            "https://image.tmdb.org/t/p/w780//jDCgWVlejIo8sQYxw3Yf1cVQUIL.jpg",
            "https://image.tmdb.org/t/p/w780//1bwF1daWnsEYYjbHXiEMdS587fR.jpg",
        )

        val titleTv = arrayOf(
            "The Good Doctor",
            "The Mandalorian",
            "Fear the Walking Dead",
            "Lucifer",
            "The Boys",
            "The Queen's Gambit",
            "The Walking Dead: World Beyond",
            "His Dark Materials",
            "Grey's Anatomy",
            "The Simpsons",
            "The 100",
            "The Umbrella Academy",
            "Game of Thrones",
            "The Vampire Diaries",
            "Supernatural",
            "Riverdale",
            "The Walking Dead",
            "I Am...",
            "Law & Order: Special Victims Unit",
            "Bones"
        )

        val popularityTv = arrayOf(
            1262.912,
            1154.887,
            726.736,
            617.445,
            523.915,
            479.349,
            470.054,
            430.44,
            406.915,
            374.233,
            359.245,
            357.818,
            348.393,
            337.107,
            330.467,
            326.896,
            316.766,
            289.684,
            284.007,
            268.797
        )

        val idTv = arrayOf(
            71712,
            82856,
            62286,
            63174,
            76479,
            87739,
            94305,
            68507,
            1416,
            456,
            48866,
            75006,
            1399,
            18165,
            1622,
            69050,
            1402,
            91605,
            2734,
            1911,
        )

        for (position in titleTv.indices){
            val itemTV = TvEntitys(
                name = titleTv[position],
                popularity = popularityTv[position],
                id = idTv[position],
                posterPath = poster[position]
            )
            tv.add(itemTV)
        }
        return tv
    }

    fun generateDummyRemoteMovie():List<ResultsItemMovie?>{
        val movies = ArrayList<ResultsItemMovie>()
        val posterMovie = arrayOf(
            "https://image.tmdb.org/t/p/w780//h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
            "https://image.tmdb.org/t/p/w780//ugZW8ocsrfgI95pnQ7wrmKDxIe.jpg",
            "https://image.tmdb.org/t/p/w780//betExZlgK0l7CZ9CsCBVcwO1OjL.jpg",
            "https://image.tmdb.org/t/p/w780//jlJ8nDhMhCYJuzOw3f52CP1W8MW.jpg",
            "https://image.tmdb.org/t/p/w780//7D430eqZj8y3oVkLFfsWXGRcpEG.jpg",
            "https://image.tmdb.org/t/p/w780//hddzYJtfYYeMDOQVcH58n8m1W3A.jpg",
            "https://image.tmdb.org/t/p/w780//elZ6JCzSEvFOq4gNjNeZsnRFsvj.jpg",
            "https://image.tmdb.org/t/p/w780//lQfdytwN7eh0tXWjIiMceFdBBvD.jpg",
            "https://image.tmdb.org/t/p/w780//riYInlsq2kf1AWoGm80JQW5dLKp.jpg",
            "https://image.tmdb.org/t/p/w780//9HT9982bzgN5on1sLRmc1GMn6ZC.jpg",
            "https://image.tmdb.org/t/p/w780//6agKYU5IQFpuDyUYPu39w7UCRrJ.jpg",
            "https://image.tmdb.org/t/p/w780//sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
            "https://image.tmdb.org/t/p/w780//aKx1ARwG55zZ0GpRvU2WrGrCG9o.jpg",
            "https://image.tmdb.org/t/p/w780//6CoRTJTmijhBLJTUNoVSUNxZMEI.jpg",
            "https://image.tmdb.org/t/p/w780//xrI4EnZWftpo1B7tTvlMUXVOikd.jpg",
            "https://image.tmdb.org/t/p/w780//qzA87Wf4jo1h8JMk9GilyIYvwsA.jpg",
            "https://image.tmdb.org/t/p/w780//kPzcvxBwt7kEISB9O4jJEuBn72t.jpg",
            "https://image.tmdb.org/t/p/w780//xqbQtMffXwa3oprse4jiHBMBvdW.jpg",
            "https://image.tmdb.org/t/p/w780//kiX7UYfOpYrMFSAGbI6j1pFkLzQ.jpg",
            "https://image.tmdb.org/t/p/w780//xqvX5A24dbIWaeYsMTxxKX5qOfz.jpg",
        )

        val title = arrayOf(
            "Demon Slayer: Kimetsu no Yaiba - The Movie: Mugen Train",
            "Hard Kill",
            "Roald Dahl's The Witches",
            "The SpongeBob Movie: Sponge on the Run",
            "2067",
            "Once Upon a Snowman",
            "Welcome to Sudden Death",
            "Over the Moon",
            "Enola Holmes",
            "Rogue City",
            "Borat Subsequent Moviefilm",
            "Peninsula",
            "Mulan",
            "Money Plane",
            "The New Mutants",
            "Ava",
            "We Bare Bears: The Movie",
            "Becky",
            "After We Collided",
            "American Pie Presents: Girls Rules",
        )

        val dateRelease = arrayOf(
            "2020-10-16",
            "2020-10-23",
            "2020-10-26",
            "2020-08-14",
            "2020-10-01",
            "2020-10-23",
            "2020-09-29",
            "2020-10-16",
            "2020-09-23",
            "2020-10-30",
            "2020-10-23",
            "2020-07-15",
            "2020-09-04",
            "2020-09-29",
            "2020-08-26",
            "2020-07-02",
            "2020-06-30",
            "2020-07-23",
            "2020-09-02",
            "2020-10-06"
        )

        val popularity = arrayOf(
            1380.001,
            1732.713,
            1661.695,
            1560.835,
            1286.086,
            1075.075,
            903.538,
            796.405,
            800.607,
            1158.648,
            681.016,
            744.918,
            657.875,
            645.159,
            672.384,
            582.577,
            570.995,
            526.037,
            772.708,
            488.34
        )

        val id = arrayOf(
            635302,
            724989,
            531219,
            400160,
            528085,
            741074,
            741067,
            560050,
            497582,
            671039,
            740985,
            581392,
            337401,
            694919,
            340102,
            539885,
            677638,
            601844,
            613504,
            660982
        )

        for (position in title.indices){
            val itemMovie = ResultsItemMovie(
                releaseDate = dateRelease[position],
                popularity = popularity[position],
                title = title[position],
                id = id[position],
                posterPath = posterMovie[position]
            )
            movies.add(itemMovie)
        }
        return movies
    }

    fun generateRemoteDummyTV():List<ResultsItemTV>{
        val tvResponse = ArrayList<ResultsItemTV>()
        val poster = arrayOf(
            "https://image.tmdb.org/t/p/w780//6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
            "https://image.tmdb.org/t/p/w780//sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
            "https://image.tmdb.org/t/p/w780//wGFUewXPeMErCe2xnCmmLEiHOGh.jpg",
            "https://image.tmdb.org/t/p/w780//4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
            "https://image.tmdb.org/t/p/w780//mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
            "https://image.tmdb.org/t/p/w780//zU0htwkhNvBQdVSIKB9s6hgVeFK.jpg",
            "https://image.tmdb.org/t/p/w780//z31GxpVgDsFAF4paZR8PRsiP16D.jpg",
            "https://image.tmdb.org/t/p/w780//g6tIKGc3f1H5QMz1dcgCwADKpZ7.jpg",
            "https://image.tmdb.org/t/p/w780//clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
            "https://image.tmdb.org/t/p/w780//2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg",
            "https://image.tmdb.org/t/p/w780//wcaDIAG1QdXQLRaj4vC1EFdBT2.jpg",
            "https://image.tmdb.org/t/p/w780//scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg",
            "https://image.tmdb.org/t/p/w780//u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
            "https://image.tmdb.org/t/p/w780//aBkVgChtyyJaHyZh1gfd8DbzQon.jpg",
            "https://image.tmdb.org/t/p/w780//KoYWXbnYuS3b0GyQPkbuexlVK9.jpg",
            "https://image.tmdb.org/t/p/w780//4X7o1ssOEvp4BFLim1AZmPNcYbU.jpg",
            "https://image.tmdb.org/t/p/w780//qgjP2OrrX9gc6M270xdPnEmE9tC.jpg",
            "https://image.tmdb.org/t/p/w780//oogunE22HDTcTxFakKQbwqfw1qo.jpg",
            "https://image.tmdb.org/t/p/w780//jDCgWVlejIo8sQYxw3Yf1cVQUIL.jpg",
            "https://image.tmdb.org/t/p/w780//1bwF1daWnsEYYjbHXiEMdS587fR.jpg",
        )

        val titleTv = arrayOf(
            "The Good Doctor",
            "The Mandalorian",
            "Fear the Walking Dead",
            "Lucifer",
            "The Boys",
            "The Queen's Gambit",
            "The Walking Dead: World Beyond",
            "His Dark Materials",
            "Grey's Anatomy",
            "The Simpsons",
            "The 100",
            "The Umbrella Academy",
            "Game of Thrones",
            "The Vampire Diaries",
            "Supernatural",
            "Riverdale",
            "The Walking Dead",
            "I Am...",
            "Law & Order: Special Victims Unit",
            "Bones"
        )

        val popularityTv = arrayOf(
            1262.912,
            1154.887,
            726.736,
            617.445,
            523.915,
            479.349,
            470.054,
            430.44,
            406.915,
            374.233,
            359.245,
            357.818,
            348.393,
            337.107,
            330.467,
            326.896,
            316.766,
            289.684,
            284.007,
            268.797
        )

        val idTv = arrayOf(
            71712,
            82856,
            62286,
            63174,
            76479,
            87739,
            94305,
            68507,
            1416,
            456,
            48866,
            75006,
            1399,
            18165,
            1622,
            69050,
            1402,
            91605,
            2734,
            1911,
        )

        for (position in titleTv.indices){
            val itemTV = ResultsItemTV(
                name = titleTv[position],
                popularity = popularityTv[position],
                id = idTv[position],
                posterPath = poster[position]
            )
            tvResponse.add(itemTV)
        }
        return tvResponse
    }

    fun generateDetailMovie():List<MovieDetailEntity>{
        val movies = ArrayList<MovieDetailEntity>()

        val posterMovie = arrayOf(
            "https://image.tmdb.org/t/p/w780//h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
            "https://image.tmdb.org/t/p/w780//ugZW8ocsrfgI95pnQ7wrmKDxIe.jpg",
            "https://image.tmdb.org/t/p/w780//betExZlgK0l7CZ9CsCBVcwO1OjL.jpg",
            "https://image.tmdb.org/t/p/w780//jlJ8nDhMhCYJuzOw3f52CP1W8MW.jpg",
            "https://image.tmdb.org/t/p/w780//7D430eqZj8y3oVkLFfsWXGRcpEG.jpg",
            "https://image.tmdb.org/t/p/w780//hddzYJtfYYeMDOQVcH58n8m1W3A.jpg",
            "https://image.tmdb.org/t/p/w780//elZ6JCzSEvFOq4gNjNeZsnRFsvj.jpg",
            "https://image.tmdb.org/t/p/w780//lQfdytwN7eh0tXWjIiMceFdBBvD.jpg",
            "https://image.tmdb.org/t/p/w780//riYInlsq2kf1AWoGm80JQW5dLKp.jpg",
            "https://image.tmdb.org/t/p/w780//9HT9982bzgN5on1sLRmc1GMn6ZC.jpg",
            "https://image.tmdb.org/t/p/w780//6agKYU5IQFpuDyUYPu39w7UCRrJ.jpg",
            "https://image.tmdb.org/t/p/w780//sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
            "https://image.tmdb.org/t/p/w780//aKx1ARwG55zZ0GpRvU2WrGrCG9o.jpg",
            "https://image.tmdb.org/t/p/w780//6CoRTJTmijhBLJTUNoVSUNxZMEI.jpg",
            "https://image.tmdb.org/t/p/w780//xrI4EnZWftpo1B7tTvlMUXVOikd.jpg",
            "https://image.tmdb.org/t/p/w780//qzA87Wf4jo1h8JMk9GilyIYvwsA.jpg",
            "https://image.tmdb.org/t/p/w780//kPzcvxBwt7kEISB9O4jJEuBn72t.jpg",
            "https://image.tmdb.org/t/p/w780//xqbQtMffXwa3oprse4jiHBMBvdW.jpg",
            "https://image.tmdb.org/t/p/w780//kiX7UYfOpYrMFSAGbI6j1pFkLzQ.jpg",
            "https://image.tmdb.org/t/p/w780//xqvX5A24dbIWaeYsMTxxKX5qOfz.jpg",
        )

        val title = arrayOf(
            "Demon Slayer: Kimetsu no Yaiba - The Movie: Mugen Train",
            "Hard Kill",
            "Roald Dahl's The Witches",
            "The SpongeBob Movie: Sponge on the Run",
            "2067",
            "Once Upon a Snowman",
            "Welcome to Sudden Death",
            "Over the Moon",
            "Enola Holmes",
            "Rogue City",
            "Borat Subsequent Moviefilm",
            "Peninsula",
            "Mulan",
            "Money Plane",
            "The New Mutants",
            "Ava",
            "We Bare Bears: The Movie",
            "Becky",
            "After We Collided",
            "American Pie Presents: Girls Rules",
        )

        val dateRelease = arrayOf(
            "2020-10-16",
            "2020-10-23",
            "2020-10-26",
            "2020-08-14",
            "2020-10-01",
            "2020-10-23",
            "2020-09-29",
            "2020-10-16",
            "2020-09-23",
            "2020-10-30",
            "2020-10-23",
            "2020-07-15",
            "2020-09-04",
            "2020-09-29",
            "2020-08-26",
            "2020-07-02",
            "2020-06-30",
            "2020-07-23",
            "2020-09-02",
            "2020-10-06"
        )

        val popularity = arrayOf(
            1380.001,
            1732.713,
            1661.695,
            1560.835,
            1286.086,
            1075.075,
            903.538,
            796.405,
            800.607,
            1158.648,
            681.016,
            744.918,
            657.875,
            645.159,
            672.384,
            582.577,
            570.995,
            526.037,
            772.708,
            488.34
        )

        val id = arrayOf(
            635302,
            724989,
            531219,
            400160,
            528085,
            741074,
            741067,
            560050,
            497582,
            671039,
            740985,
            581392,
            337401,
            694919,
            340102,
            539885,
            677638,
            601844,
            613504,
            660982
        )

        val overview = arrayOf(
            """Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!
            """.trimIndent(),
            """The work of billionaire tech CEO Donovan Chalmers is so valuable that he hires mercenaries to protect it, and a terrorist group kidnaps his daughter just to get it.
            """.trimIndent(),
            """In late 1967, a young orphaned boy goes to live with his loving grandma in the rural Alabama town of Demopolis. As the boy and his grandmother encounter some deceptively glamorous but thoroughly diabolical witches, she wisely whisks him away to a seaside resort. Regrettably, they arrive at precisely the same time that the world's Grand High Witch has gathered.
            """.trimIndent(),
            """When his best friend Gary is suddenly snatched away, SpongeBob takes Patrick on a madcap mission far beyond Bikini Bottom to save their pink-shelled pal.
            """.trimIndent(),
            """A lowly utility worker is called to the future by a mysterious radio signal, he must leave his dying wife to embark on a journey that will force him to face his deepest fears in an attempt to change the fabric of reality and save humankind from its greatest environmental crisis yet.
            """.trimIndent(),
            """The previously untold origins of Olaf, the innocent and insightful, summer-loving snowman are revealed as we follow Olaf’s first steps as he comes to life and searches for his identity in the snowy mountains outside Arendelle.
            """.trimIndent(),
            """Jesse Freeman is a former special forces officer and explosives expert now working a regular job as a security guard in a state-of-the-art basketball arena. Trouble erupts when a tech-savvy cadre of terrorists kidnap the team's owner and Jesse's daughter during opening night. Facing a ticking clock and impossible odds, it's up to Jesse to not only save them but also a full house of fans in this highly charged action thriller.
            """.trimIndent(),
            """A girl builds a rocket to travel to the moon in hopes of meeting the legendary Moon Goddess.
            """.trimIndent(),
            """While searching for her missing mother, intrepid teen Enola Holmes uses her sleuthing skills to outsmart big brother Sherlock and help a runaway lord.
            """.trimIndent(),
            """Caught in the crosshairs of police corruption and Marseille’s warring gangs, a loyal cop must protect his squad by taking matters into his own hands.
            """.trimIndent(),
            """14 years after making a film about his journey across the USA, Borat risks life and limb when he returns to the United States with his young daughter, and reveals more about the culture, the COVID-19 pandemic, and the political elections.
            """.trimIndent(),
            """A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.
            """.trimIndent(),
            """When the Emperor of China issues a decree that one man per family must serve in the Imperial Chinese Army to defend the country from Huns, Hua Mulan, the eldest daughter of an honored warrior, steps in to take the place of her ailing father. She is spirited, determined and quick on her feet. Disguised as a man by the name of Hua Jun, she is tested every step of the way and must harness her innermost strength and embrace her true potential.
            """.trimIndent(),
            """A professional thief with $40 million in debt and his family's life on the line must commit one final heist - rob a futuristic airborne casino filled with the world's most dangerous criminals.
            """.trimIndent(),
            """Five young mutants, just discovering their abilities while held in a secret facility against their will, fight to escape their past sins and save themselves.
            """.trimIndent(),
            """A black ops assassin is forced to fight for her own survival after a job goes dangerously wrong.
            """.trimIndent(),
            """When Grizz, Panda, and Ice Bear's love of food trucks and viral videos get out of hand, the brothers are chased away from their home and embark on a trip to Canada, where they can live in peace.
            """.trimIndent(),
            """A teenager's weekend at a lake house with her father takes a turn for the worse when a group of convicts wreaks havoc on their lives.
            """.trimIndent(),
            """Tessa finds herself struggling with her complicated relationship with Hardin; she faces a dilemma that could change their lives forever.
            """.trimIndent(),
            """It's Senior year at East Great Falls. Annie, Kayla, Michelle, and Stephanie decide to harness their girl power and band together to get what they want their last year of high school.
            """.trimIndent()
        )

        for (position in title.indices){
            val itemMovie = MovieDetailEntity(
                overview = overview[position],
                releaseDate = dateRelease[position],
                popularity = popularity[position],
                title = title[position],
                id = id[position],
                posterPath = posterMovie[position]
            )
            movies.add(itemMovie)
        }
        return movies
    }

    fun generateDetailDummyTV():List<TvDetailEntity>{
        val tvResponse = ArrayList<TvDetailEntity>()
        val poster = arrayOf(
            "https://image.tmdb.org/t/p/w780//6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
            "https://image.tmdb.org/t/p/w780//sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
            "https://image.tmdb.org/t/p/w780//wGFUewXPeMErCe2xnCmmLEiHOGh.jpg",
            "https://image.tmdb.org/t/p/w780//4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
            "https://image.tmdb.org/t/p/w780//mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
            "https://image.tmdb.org/t/p/w780//zU0htwkhNvBQdVSIKB9s6hgVeFK.jpg",
            "https://image.tmdb.org/t/p/w780//z31GxpVgDsFAF4paZR8PRsiP16D.jpg",
            "https://image.tmdb.org/t/p/w780//g6tIKGc3f1H5QMz1dcgCwADKpZ7.jpg",
            "https://image.tmdb.org/t/p/w780//clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
            "https://image.tmdb.org/t/p/w780//2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg",
            "https://image.tmdb.org/t/p/w780//wcaDIAG1QdXQLRaj4vC1EFdBT2.jpg",
            "https://image.tmdb.org/t/p/w780//scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg",
            "https://image.tmdb.org/t/p/w780//u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
            "https://image.tmdb.org/t/p/w780//aBkVgChtyyJaHyZh1gfd8DbzQon.jpg",
            "https://image.tmdb.org/t/p/w780//KoYWXbnYuS3b0GyQPkbuexlVK9.jpg",
            "https://image.tmdb.org/t/p/w780//4X7o1ssOEvp4BFLim1AZmPNcYbU.jpg",
            "https://image.tmdb.org/t/p/w780//qgjP2OrrX9gc6M270xdPnEmE9tC.jpg",
            "https://image.tmdb.org/t/p/w780//oogunE22HDTcTxFakKQbwqfw1qo.jpg",
            "https://image.tmdb.org/t/p/w780//jDCgWVlejIo8sQYxw3Yf1cVQUIL.jpg",
            "https://image.tmdb.org/t/p/w780//1bwF1daWnsEYYjbHXiEMdS587fR.jpg",
        )

        val titleTv = arrayOf(
            "The Good Doctor",
            "The Mandalorian",
            "Fear the Walking Dead",
            "Lucifer",
            "The Boys",
            "The Queen's Gambit",
            "The Walking Dead: World Beyond",
            "His Dark Materials",
            "Grey's Anatomy",
            "The Simpsons",
            "The 100",
            "The Umbrella Academy",
            "Game of Thrones",
            "The Vampire Diaries",
            "Supernatural",
            "Riverdale",
            "The Walking Dead",
            "I Am...",
            "Law & Order: Special Victims Unit",
            "Bones"
        )

        val popularityTv = arrayOf(
            1262.912,
            1154.887,
            726.736,
            617.445,
            523.915,
            479.349,
            470.054,
            430.44,
            406.915,
            374.233,
            359.245,
            357.818,
            348.393,
            337.107,
            330.467,
            326.896,
            316.766,
            289.684,
            284.007,
            268.797
        )

        val idTv = arrayOf(
            71712,
            82856,
            62286,
            63174,
            76479,
            87739,
            94305,
            68507,
            1416,
            456,
            48866,
            75006,
            1399,
            18165,
            1622,
            69050,
            1402,
            91605,
            2734,
            1911,
        )

        val description = arrayOf(
            """A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?
            """.trimIndent(),
            """After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.
            """.trimIndent(),
            """What did the world look like as it was transforming into the horrifying apocalypse depicted in "The Walking Dead"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.
            """.trimIndent(),
            """Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.
            """.trimIndent(),
            """A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.
            """.trimIndent(),
            """In a Kentucky orphanage in the 1950s, a young girl discovers an astonishing talent for chess while struggling with addiction.
            """.trimIndent(),
            """A heroic group of teens sheltered from the dangers of the post-apocalyptic world receive a message that inspires them to leave the safety of the only home they have ever known and embark on a cross-country journey to find the one man who can possibly save the world.
            """.trimIndent(),
            """Lyra is an orphan who lives in a parallel universe in which science, theology and magic are entwined. Lyra's search for a kidnapped friend uncovers a sinister plot involving stolen children, and turns into a quest to understand a mysterious phenomenon called Dust. She is later joined on her journey by Will, a boy who possesses a knife that can cut windows between worlds. As Lyra learns the truth about her parents and her prophesied destiny, the two young people are caught up in a war against celestial powers that ranges across many worlds.
            """.trimIndent(),
            """Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital. 
            """.trimIndent(),
            """Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.
            """.trimIndent(),
            """100 years in the future, when the Earth has been abandoned due to radioactivity, the last surviving humans live on an ark orbiting the planet — but the ark won't last forever. So the repressive regime picks 100 expendable juvenile delinquents to send down to Earth to see if the planet is still habitable.
            """.trimIndent(),
            """A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more. 
            """.trimIndent(),
            """Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.
            """.trimIndent(),
            """The story of two vampire brothers obsessed with the same girl, who bears a striking resemblance to the beautiful but ruthless vampire they knew and loved in 1864.
            """.trimIndent(),
            """When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way. 
            """.trimIndent(),
            """Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.
            """.trimIndent(),
            """Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.
            """.trimIndent(),
            """Each hour-long film follows a different women as they experience “moments that are emotionally raw, thought-provoking and utterly personal”.
            """.trimIndent(),
            """In the criminal justice system, sexually-based offenses are considered especially heinous. In New York City, the dedicated detectives who investigate these vicious felonies are members of an elite squad known as the Special Victims Unit. These are their stories.
            """.trimIndent(),
            """Dr. Temperance Brennan and her colleagues at the Jeffersonian's Medico-Legal Lab assist Special Agent Seeley Booth with murder investigations when the remains are so badly decomposed, burned or destroyed that the standard identification methods are useless.
            """.trimIndent()
        )

        for (position in titleTv.indices){
            val itemTV = TvDetailEntity(
                originalName = titleTv[position],
                overview = description[position],
                popularity = popularityTv[position],
                id = idTv[position],
                posterPath = poster[position]
            )
            tvResponse.add(itemTV)
        }
        return tvResponse
    }

    fun generateRemoteDetailMovie():MovieDetailResponse{

        val posterMovie = arrayOf(
            "https://image.tmdb.org/t/p/w780/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
            "https://image.tmdb.org/t/p/w780/ugZW8ocsrfgI95pnQ7wrmKDxIe.jpg",
            "https://image.tmdb.org/t/p/w780/betExZlgK0l7CZ9CsCBVcwO1OjL.jpg",
            "https://image.tmdb.org/t/p/w780/7D430eqZj8y3oVkLFfsWXGRcpEG.jpg",
            "https://image.tmdb.org/t/p/w780/zfdhsR3Y3xw42OHrMpi0oBw0Uk8.jpg",
            "https://image.tmdb.org/t/p/w780/lQfdytwN7eh0tXWjIiMceFdBBvD.jpg",
            "https://image.tmdb.org/t/p/w780/elZ6JCzSEvFOq4gNjNeZsnRFsvj.jpg",
            "https://image.tmdb.org/t/p/w780/oAuBztpKuTIHb8nLl6miXIy0Sj9.jpg",
            "https://image.tmdb.org/t/p/w780/riYInlsq2kf1AWoGm80JQW5dLKp.jpg",
            "https://image.tmdb.org/t/p/w780/6agKYU5IQFpuDyUYPu39w7UCRrJ.jpg",
            "https://image.tmdb.org/t/p/w780/aKx1ARwG55zZ0GpRvU2WrGrCG9o.jpg",
            "https://image.tmdb.org/t/p/w780/6CoRTJTmijhBLJTUNoVSUNxZMEI.jpg",
            "https://image.tmdb.org/t/p/w780/kPzcvxBwt7kEISB9O4jJEuBn72t.jpg",
            "https://image.tmdb.org/t/p/w780/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
            "https://image.tmdb.org/t/p/w780/qzA87Wf4jo1h8JMk9GilyIYvwsA.jpg",
            "https://image.tmdb.org/t/p/w780/xqbQtMffXwa3oprse4jiHBMBvdW.jpg",
            "https://image.tmdb.org/t/p/w780/kiX7UYfOpYrMFSAGbI6j1pFkLzQ.jpg",
            "https://image.tmdb.org/t/p/w780/5aL71e0XBgHZ6zdWcWeuEhwD2Gw.jpg",
            "https://image.tmdb.org/t/p/w780/r4Lm1XKP0VsTgHX4LG4syAwYA2I.jpg",
            "https://image.tmdb.org/t/p/w780/9Rj8l6gElLpRL7Kj17iZhrT5Zuw.jpg",
        )

        val title = arrayOf(
            "Demon Slayer: Kimetsu no Yaiba - The Movie: Mugen Train",
            "Hard Kill",
            "Roald Dahl's The Witches",
            "2067",
            "Once Upon a Snowman",
            "Over the Moon",
            "Welcome to Sudden Death",
            "I Believe",
            "Enola Holmes",
            "Borat Subsequent Moviefilm",
            "Mulan",
            "Money Plane",
            "We Bare Bears: The Movie",
            "Peninsula",
            "Ava",
            "Becky",
            "After We Collided",
            "Happy Halloween Scooby-Doo!",
            "Love and Monsters",
            "Santana",
        )

        val dateRelease = arrayOf(
            "2020-10-16",
            "2020-10-23",
            "2020-10-26",
            "2020-10-01",
            "2020-10-23",
            "2020-10-16",
            "2020-09-29",
            "2020-11-05",
            "2020-09-23",
            "2020-10-23",
            "2020-09-04",
            "2020-09-29",
            "2020-06-30",
            "2020-07-15",
            "2020-07-02",
            "2020-07-23",
            "2020-09-02",
            "2020-10-06",
            "2020-10-16",
            "2020-08-28"
        )

        val popularity = arrayOf(
            3465.935,
            2110.115,
            2170.839,
            1528.35,
            1373.216,
            1179.053,
            1013.167,
            1016.073,
            926.189,
            865.745,
            827.458,
            719.388,
            941.604,
            792.946,
            691.497,
            628.09,
            496.579,
            559.595,
            565.703,
            541.153
        )

        val id = arrayOf(
            635302,
            724989,
            531219,
            528085,
            741074,
            560050,
            741067,
            499338,
            497582,
            740985,
            337401,
            694919,
            677638,
            581392,
            539885,
            601844,
            613504,
            721656,
            590223,
            734309
        )

        val overview = arrayOf(
            """Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!
            """.trimIndent(),
            """The work of billionaire tech CEO Donovan Chalmers is so valuable that he hires mercenaries to protect it, and a terrorist group kidnaps his daughter just to get it.
            """.trimIndent(),
            """In late 1967, a young orphaned boy goes to live with his loving grandma in the rural Alabama town of Demopolis. As the boy and his grandmother encounter some deceptively glamorous but thoroughly diabolical witches, she wisely whisks him away to a seaside resort. Regrettably, they arrive at precisely the same time that the world's Grand High Witch has gathered.
            """.trimIndent(),
            """A lowly utility worker is called to the future by a mysterious radio signal, he must leave his dying wife to embark on a journey that will force him to face his deepest fears in an attempt to change the fabric of reality and save humankind from its greatest environmental crisis yet.
            """.trimIndent(),
            """The previously untold origins of Olaf, the innocent and insightful, summer-loving snowman are revealed as we follow Olaf’s first steps as he comes to life and searches for his identity in the snowy mountains outside Arendelle.
            """.trimIndent(),
            """A girl builds a rocket to travel to the moon in hopes of meeting the legendary Moon Goddess.
            """.trimIndent(),
            """Jesse Freeman is a former special forces officer and explosives expert now working a regular job as a security guard in a state-of-the-art basketball arena. Trouble erupts when a tech-savvy cadre of terrorists kidnap the team's owner and Jesse's daughter during opening night. Facing a ticking clock and impossible odds, it's up to Jesse to not only save them but also a full house of fans in this highly charged action thriller.
            """.trimIndent(),
            """A 9 year old boy experiences God's power in a supernatural way.
            """.trimIndent(),
            """While searching for her missing mother, intrepid teen Enola Holmes uses her sleuthing skills to outsmart big brother Sherlock and help a runaway lord.
            """.trimIndent(),
            """14 years after making a film about his journey across the USA, Borat risks life and limb when he returns to the United States with his young daughter, and reveals more about the culture, the COVID-19 pandemic, and the political elections.
            """.trimIndent(),
            """When the Emperor of China issues a decree that one man per family must serve in the Imperial Chinese Army to defend the country from Huns, Hua Mulan, the eldest daughter of an honored warrior, steps in to take the place of her ailing father. She is spirited, determined and quick on her feet. Disguised as a man by the name of Hua Jun, she is tested every step of the way and must harness her innermost strength and embrace her true potential.
            """.trimIndent(),
            """A professional thief with $40 million in debt and his family's life on the line must commit one final heist - rob a futuristic airborne casino filled with the world's most dangerous criminals.
            """.trimIndent(),
            """When Grizz, Panda, and Ice Bear's love of food trucks and viral videos get out of hand, the brothers are chased away from their home and embark on a trip to Canada, where they can live in peace.
            """.trimIndent(),
            """A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.
            """.trimIndent(),
            """A black ops assassin is forced to fight for her own survival after a job goes dangerously wrong.
            """.trimIndent(),
            """A teenager's weekend at a lake house with her father takes a turn for the worse when a group of convicts wreaks havoc on their lives.
            """.trimIndent(),
            """Tessa finds herself struggling with her complicated relationship with Hardin; she faces a dilemma that could change their lives forever.
            """.trimIndent(),
            """Scooby-Doo and the gang team up with their pals, Bill Nye The Science Guy and Elvira Mistress of the Dark, to solve this mystery of gigantic proportions and save Crystal Cove!
            """.trimIndent(),
            """Seven years after the Monsterpocalypse, Joel Dawson, along with the rest of humanity, has been living underground ever since giant creatures took control of the land. After reconnecting over radio with his high school girlfriend Aimee, who is now 80 miles away at a coastal colony, Joel begins to fall for her again. As Joel realizes that there’s nothing left for him underground, he decides against all logic to venture out to Aimee, despite all the dangerous monsters that stand in his way.
            """.trimIndent(),
            """Two brothers — one a narcotics agent and the other a general — finally discover the identity of the drug lord who murdered their parents decades ago. They may kill each other before capturing the bad guys.
            """.trimIndent()
        )

        lateinit var itemMovie:MovieDetailResponse
        for (position in title.indices){
            itemMovie = MovieDetailResponse(
                overview = overview[position],
                releaseDate = dateRelease[position],
                popularity = popularity[position],
                title = title[position],
                id = id[position],
                posterPath = posterMovie[position]
            )
        }
        return itemMovie
    }

    fun generateRemoteDetailDummyTV(): TvDetailResponse {
        val poster = arrayOf(
            "https://image.tmdb.org/t/p/w780/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
            "https://image.tmdb.org/t/p/w780/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
            "https://image.tmdb.org/t/p/w780/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
            "https://image.tmdb.org/t/p/w780/wGFUewXPeMErCe2xnCmmLEiHOGh.jpg",
            "https://image.tmdb.org/t/p/w780/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
            "https://image.tmdb.org/t/p/w780/zU0htwkhNvBQdVSIKB9s6hgVeFK.jpg",
            "https://image.tmdb.org/t/p/w780/z31GxpVgDsFAF4paZR8PRsiP16D.jpg",
            "https://image.tmdb.org/t/p/w780/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
            "https://image.tmdb.org/t/p/w780/KoYWXbnYuS3b0GyQPkbuexlVK9.jpg",
            "https://image.tmdb.org/t/p/w780/scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg",
            "https://image.tmdb.org/t/p/w780/qcr9bBY6MVeLzriKCmJOv1562uY.jpg",
            "https://image.tmdb.org/t/p/w780/wcaDIAG1QdXQLRaj4vC1EFdBT2.jpg",
            "https://image.tmdb.org/t/p/w780/eTMMU2rKpZRbo9ERytyhwatwAjz.jpg",
            "https://image.tmdb.org/t/p/w780/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
            "https://image.tmdb.org/t/p/w780/aBkVgChtyyJaHyZh1gfd8DbzQon.jpg",
            "https://image.tmdb.org/t/p/w780/qgjP2OrrX9gc6M270xdPnEmE9tC.jpg",
            "https://image.tmdb.org/t/p/w780/4X7o1ssOEvp4BFLim1AZmPNcYbU.jpg",
            "https://image.tmdb.org/t/p/w780/jDCgWVlejIo8sQYxw3Yf1cVQUIL.jpg",
            "https://image.tmdb.org/t/p/w780/ri1WF0vxDfJcUW8iNUOu8OsmVeJ.jpg",
            "https://image.tmdb.org/t/p/w780/1bwF1daWnsEYYjbHXiEMdS587fR.jpg",
        )

        val titleTv = arrayOf(
            "The Mandalorian",
            "The Good Doctor",
            "Lucifer",
            "Fear the Walking Dead",
            "The Boys",
            "The Queen's Gambit",
            "The Walking Dead: World Beyond",
            "Grey's Anatomy",
            "Supernatural",
            "The Umbrella Academy",
            "The Simpsons",
            "The 100",
            "Cobra Kai",
            "Game of Thrones",
            "The Vampire Diaries",
            "The Walking Dead",
            "Riverdale",
            "Law & Order: Special Victims Unit",
            "Suburra: Blood on Rome",
            "Bones"
        )

        val popularityTv = arrayOf(
            2061.405,
            1434.392,
            627.192,
            601.84,
            617.198,
            485.529,
            397.936,
            369.868,
            343.723,
            330.621,
            351.639,
            342.901,
            334.129,
            356.259,
            323.306,
            321.51,
            333.655,
            258.824,
            284.552,
            271.276
        )

        val idTv = arrayOf(
            82856,
            71712,
            63174,
            62286,
            76479,
            87739,
            94305,
            1416,
            1622,
            75006,
            456,
            48866,
            77169,
            1399,
            18165,
            1402,
            69050,
            2734,
            73671,
            1911
        )

        val description = arrayOf(
            """After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.
            """.trimIndent(),
            """A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?
            """.trimIndent(),
            """Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.
            """.trimIndent(),
            """What did the world look like as it was transforming into the horrifying apocalypse depicted in "The Walking Dead"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.
            """.trimIndent(),
            """A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.
            """.trimIndent(),
            """In a Kentucky orphanage in the 1950s, a young girl discovers an astonishing talent for chess while struggling with addiction.
            """.trimIndent(),
            """A heroic group of teens sheltered from the dangers of the post-apocalyptic world receive a message that inspires them to leave the safety of the only home they have ever known and embark on a cross-country journey to find the one man who can possibly save the world.
            """.trimIndent(),
            """Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.
            """.trimIndent(),
            """When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way. 
            """.trimIndent(),
            """A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.
            """.trimIndent(),
            """Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.
            """.trimIndent(),
            """100 years in the future, when the Earth has been abandoned due to radioactivity, the last surviving humans live on an ark orbiting the planet — but the ark won't last forever. So the repressive regime picks 100 expendable juvenile delinquents to send down to Earth to see if the planet is still habitable. 
            """.trimIndent(),
            """This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.
            """.trimIndent(),
            """Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.
            """.trimIndent(),
            """The story of two vampire brothers obsessed with the same girl, who bears a striking resemblance to the beautiful but ruthless vampire they knew and loved in 1864.
            """.trimIndent(),
            """Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.
            """.trimIndent(),
            """Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.
            """.trimIndent(),
            """In the criminal justice system, sexually-based offenses are considered especially heinous. In New York City, the dedicated detectives who investigate these vicious felonies are members of an elite squad known as the Special Victims Unit. These are their stories.
            """.trimIndent(),
            """In 2008, a fight over land in a seaside town near Rome spirals into a deadly battle between organized crime, corrupt politicians and the Vatican.
            """.trimIndent(),
            """Dr. Temperance Brennan and her colleagues at the Jeffersonian's Medico-Legal Lab assist Special Agent Seeley Booth with murder investigations when the remains are so badly decomposed, burned or destroyed that the standard identification methods are useless.
            """.trimIndent()
        )

        lateinit var itemTV:TvDetailResponse
        for (position in titleTv.indices){
            itemTV = TvDetailResponse(
                originalName = titleTv[position],
                overview = description[position],
                popularity = popularityTv[position],
                id = idTv[position],
                posterPath = poster[position]
            )
        }
        return itemTV
    }

    fun generateRemoteUpcominglDummyMovie():List<ResultsItemUpMovie>{
        val tvResponse = ArrayList<ResultsItemUpMovie>()
        val poster = arrayOf(
            "https://image.tmdb.org/t/p/w780/betExZlgK0l7CZ9CsCBVcwO1OjL.jpg",
            "https://image.tmdb.org/t/p/w780/uOw5JD8IlD546feZ6oxbIjvN66P.jpg",
            "https://image.tmdb.org/t/p/w780/3eg0kGC2Xh0vhydJHO37Sp4cmMt.jpg",
            "https://image.tmdb.org/t/p/w780/8F9xUvb1JMWUMkFV2Yq3aiueAbq.jpg",
            "https://image.tmdb.org/t/p/w780/ltyARDw2EFXZ2H2ERnlEctXPioP.jpg",
            "https://image.tmdb.org/t/p/w780/4BgSWFMW2MJ0dT5metLzsRWO7IJ.jpg",
            "https://image.tmdb.org/t/p/w780/2EiAX4eChSWQHwgIFAZbPgXKCJ6.jpg",
            "https://image.tmdb.org/t/p/w780/lhMIra0pqWNuD6CIXoTmGwZ0EBS.jpg",
            "https://image.tmdb.org/t/p/w780/maBFNvaxae6IgLL4qwz7hvz19EZ.jpg",
            "https://image.tmdb.org/t/p/w780/oxxIOWDP6beG37VG3jMxE77pRwC.jpg",
            "https://image.tmdb.org/t/p/w780/wGkr4r1e8nubmSNKJpv3HL6sFrA.jpg",
            "https://image.tmdb.org/t/p/w780/5DuSFFEn41ZDc6mSqMm0twrWGn.jpg",
            "https://image.tmdb.org/t/p/w780/dqA2FCzz4OMmXLitKopzf476RVB.jpg",
            "https://image.tmdb.org/t/p/w780/lI9JWDutOcN7MOfprddggEK5XdN.jpg",
            "https://image.tmdb.org/t/p/w780/iFcSfoMu9hQIX4t0CxIkDJKgIES.jpg",
            "https://image.tmdb.org/t/p/w780/yf5IuMW6GHghu39kxA0oFx7Bxmj.jpg",
            "https://image.tmdb.org/t/p/w780/xKCtoYHUyX8zAg68eemnYa2orep.jpg",
            "https://image.tmdb.org/t/p/w780/2QKNREj8xPHShu993QAySoGDCwu.jpg",
            "https://image.tmdb.org/t/p/w780/iSwTnNS7TKAS79Sz9LvyqlBxxrU.jpg",
            "https://image.tmdb.org/t/p/w780/gBRM1EgfslcxcZCSf6Vp89VYCmP.jpg",
        )

        val title = arrayOf(
            "Roald Dahl's The Witches", "Rogue", "The Tax Collector", "Death of Me",
            "The War with Grandpa", "Cats & Dogs 3: Paws Unite", "1BR", "The Craft: Legacy",
            "Cranston Academy: Monster Zone", "Monos","The Broken Hearts Gallery","The Croods: A New Age","I Still Believe",
            "劇場版「Fate/stay night [Heaven’s Feel]」Ⅲ.spring song","Le prince oublié","Palm Springs","Given","Children of the Sea","Shirley",
            "The Empty Man!"
        )

        val description = arrayOf(
            """After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.
            """.trimIndent(),
            """What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.
            """.trimIndent(),
            """A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.
            """.trimIndent(),
            """Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.
            """.trimIndent(),
            """In a Kentucky orphanage in the 1950s, a young girl discovers an astonishing talent for chess while struggling with addiction.
            """.trimIndent(),
            """A heroic group of teens sheltered from the dangers of the post-apocalyptic world receive a message that inspires them to leave the safety of the only home they have ever known and embark on a cross-country journey to find the one man who can possibly save the world.
            """.trimIndent(),
            """A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?
            """.trimIndent(),
            """This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.
            """.trimIndent(),
            """In 2008, a fight over land in a seaside town near Rome spirals into a deadly battle between organized crime, corrupt politicians and the Vatican.
            """.trimIndent(),
            """Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.
            """.trimIndent(),
            """Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.
            """.trimIndent(),
            """When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way. 
            """.trimIndent(),
            """The story of two vampire brothers obsessed with the same girl, who bears a striking resemblance to the beautiful but ruthless vampire they knew and loved in 1864.
            """.trimIndent(),
            """100 years in the future, when the Earth has been abandoned due to radioactivity, the last surviving humans live on an ark orbiting the planet — but the ark won't last forever. So the repressive regime picks 100 expendable juvenile delinquents to send down to Earth to see if the planet is still habitable.
            """.trimIndent(),
            """Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.
            """.trimIndent(),
            """Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.
            """.trimIndent(),
            """A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.
            """.trimIndent(),
            """Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.
            """.trimIndent(),
            """Each hour-long film follows a different women as they experience “moments that are emotionally raw, thought-provoking and utterly personal
            """.trimIndent(),
            """After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash
            """.trimIndent()
        )

        val popularityTv = arrayOf(
            1768.318, 560.202, 469.664, 379.519, 300.419, 262.993, 258.127, 119.476,
            126.438, 102.554, 95.607, 90.173, 91.237, 72.397, 77.968, 69.25, 55.455, 45.144, 44.263, 51.418
        )

        val idTv = arrayOf(
            531219, 718444, 531499, 595149, 425001, 726739, 611605, 590995, 627661, 417466, 616251, 529203,
            585244, 390635, 520725, 587792, 632632, 585077, 547017, 516632
        )

        for (position in title.indices){
            val itemTV = ResultsItemUpMovie(
                title = title[position],
                popularity = popularityTv[position],
                id = idTv[position],
                posterPath = poster[position]
            )
            tvResponse.add(itemTV)
        }
        return tvResponse
    }

    fun generateUpcominglDummyMovie():List<UpcomingMovieEntity>{
        val tvResponse = ArrayList<UpcomingMovieEntity>()
        val poster = arrayOf(
            "https://image.tmdb.org/t/p/w780/betExZlgK0l7CZ9CsCBVcwO1OjL.jpg",
            "https://image.tmdb.org/t/p/w780/uOw5JD8IlD546feZ6oxbIjvN66P.jpg",
            "https://image.tmdb.org/t/p/w780/3eg0kGC2Xh0vhydJHO37Sp4cmMt.jpg",
            "https://image.tmdb.org/t/p/w780/8F9xUvb1JMWUMkFV2Yq3aiueAbq.jpg",
            "https://image.tmdb.org/t/p/w780/ltyARDw2EFXZ2H2ERnlEctXPioP.jpg",
            "https://image.tmdb.org/t/p/w780/4BgSWFMW2MJ0dT5metLzsRWO7IJ.jpg",
            "https://image.tmdb.org/t/p/w780/2EiAX4eChSWQHwgIFAZbPgXKCJ6.jpg",
            "https://image.tmdb.org/t/p/w780/lhMIra0pqWNuD6CIXoTmGwZ0EBS.jpg",
            "https://image.tmdb.org/t/p/w780/maBFNvaxae6IgLL4qwz7hvz19EZ.jpg",
            "https://image.tmdb.org/t/p/w780/oxxIOWDP6beG37VG3jMxE77pRwC.jpg",
            "https://image.tmdb.org/t/p/w780/wGkr4r1e8nubmSNKJpv3HL6sFrA.jpg",
            "https://image.tmdb.org/t/p/w780/5DuSFFEn41ZDc6mSqMm0twrWGn.jpg",
            "https://image.tmdb.org/t/p/w780/dqA2FCzz4OMmXLitKopzf476RVB.jpg",
            "https://image.tmdb.org/t/p/w780/lI9JWDutOcN7MOfprddggEK5XdN.jpg",
            "https://image.tmdb.org/t/p/w780/iFcSfoMu9hQIX4t0CxIkDJKgIES.jpg",
            "https://image.tmdb.org/t/p/w780/yf5IuMW6GHghu39kxA0oFx7Bxmj.jpg",
            "https://image.tmdb.org/t/p/w780/xKCtoYHUyX8zAg68eemnYa2orep.jpg",
            "https://image.tmdb.org/t/p/w780/2QKNREj8xPHShu993QAySoGDCwu.jpg",
            "https://image.tmdb.org/t/p/w780/iSwTnNS7TKAS79Sz9LvyqlBxxrU.jpg",
            "https://image.tmdb.org/t/p/w780/gBRM1EgfslcxcZCSf6Vp89VYCmP.jpg",
        )

        val title = arrayOf(
            "Roald Dahl's The Witches", "Rogue", "The Tax Collector", "Death of Me",
            "The War with Grandpa", "Cats & Dogs 3: Paws Unite", "1BR", "The Craft: Legacy",
            "Cranston Academy: Monster Zone", "Monos","The Broken Hearts Gallery","The Croods: A New Age","I Still Believe",
            "劇場版「Fate/stay night [Heaven’s Feel]」Ⅲ.spring song","Le prince oublié","Palm Springs","Given","Children of the Sea","Shirley",
            "The Empty Man!"
        )

        val description = arrayOf(
            """After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.
            """.trimIndent(),
            """What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.
            """.trimIndent(),
            """A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.
            """.trimIndent(),
            """Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.
            """.trimIndent(),
            """In a Kentucky orphanage in the 1950s, a young girl discovers an astonishing talent for chess while struggling with addiction.
            """.trimIndent(),
            """A heroic group of teens sheltered from the dangers of the post-apocalyptic world receive a message that inspires them to leave the safety of the only home they have ever known and embark on a cross-country journey to find the one man who can possibly save the world.
            """.trimIndent(),
            """A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?
            """.trimIndent(),
            """This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.
            """.trimIndent(),
            """In 2008, a fight over land in a seaside town near Rome spirals into a deadly battle between organized crime, corrupt politicians and the Vatican.
            """.trimIndent(),
            """Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.
            """.trimIndent(),
            """Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.
            """.trimIndent(),
            """When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way. 
            """.trimIndent(),
            """The story of two vampire brothers obsessed with the same girl, who bears a striking resemblance to the beautiful but ruthless vampire they knew and loved in 1864.
            """.trimIndent(),
            """100 years in the future, when the Earth has been abandoned due to radioactivity, the last surviving humans live on an ark orbiting the planet — but the ark won't last forever. So the repressive regime picks 100 expendable juvenile delinquents to send down to Earth to see if the planet is still habitable.
            """.trimIndent(),
            """Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.
            """.trimIndent(),
            """Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.
            """.trimIndent(),
            """A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.
            """.trimIndent(),
            """Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.
            """.trimIndent(),
            """Each hour-long film follows a different women as they experience “moments that are emotionally raw, thought-provoking and utterly personal
            """.trimIndent(),
            """After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash
            """.trimIndent()
        )

        val popularityTv = arrayOf(
            1768.318,
            560.202,
            469.664,
            379.519,
            300.419,
            262.993,
            258.127,
            119.476,
            126.438,
            102.554,
            95.607,
            90.173,
            91.237,
            72.397,
            77.968,
            69.25,
            55.455,
            45.144,
            44.263,
            51.418
        )

        val idTv = arrayOf(
            531219,
            718444,
            531499,
            595149,
            425001,
            726739,
            611605,
            590995,
            627661,
            417466,
            616251,
            529203,
            585244,
            390635,
            520725,
            587792,
            632632,
            585077,
            547017,
            516632
        )

        for (position in title.indices){
            val itemTV = UpcomingMovieEntity(
                title = title[position],
                popularity = popularityTv[position],
                id = idTv[position],
                posterPath = poster[position]
            )
            tvResponse.add(itemTV)
        }
        return tvResponse
    }

    fun generateUpcominglDummyTv():List<UpcomingTvEntity>{
        val tvResponse = ArrayList<UpcomingTvEntity>()
        val poster = arrayOf(
            "https://image.tmdb.org/t/p/w780/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
            "https://image.tmdb.org/t/p/w780/aESxB2HblKlDzma39xVefa20pbW.jpg",
            "https://image.tmdb.org/t/p/w780/98RYSYsRNKWgrAAFBn0WfploUG7.jpg",
            "https://image.tmdb.org/t/p/w780/rOuGm07PxBhEsK9TaGPRQVJQm1X.jpg",
            "https://image.tmdb.org/t/p/w780/l7QHRrX1EYgQAzNJdCdoEQHoHJ.jpg",
            "https://image.tmdb.org/t/p/w780/vrbqaBXB8AALynQzpWz6JdCPEJS.jpg",
            "https://image.tmdb.org/t/p/w780/maBJkaBM4UqAttn9UkLCfZEVEfk.jpg",
            "https://image.tmdb.org/t/p/w780/eLPxEM7WAvljjFv4UwJ1awhANa9.jpg",
            "https://image.tmdb.org/t/p/w780/ualtuRRNYa9Fj61wSuBVcuvaVDP.jpg",
            "https://image.tmdb.org/t/p/w780/z4jgyI5TpoRZiJTNchkVkMrGQyz.jpg",
            "https://image.tmdb.org/t/p/w780/tGXZEUgSbgufoOpXMImSKPDd5A3.jpg",
            "https://image.tmdb.org/t/p/w780/xe6y8SJU0NyGEECu2LV9cXoY81g.jpg",
            "https://image.tmdb.org/t/p/w780/t7sj18MvyNEKcPEssw3LngX6Ewl.jpg",
            "https://image.tmdb.org/t/p/w780/fCEc46PoQ5uZlX4917jEYY3Ohoh.jpg",
            "https://image.tmdb.org/t/p/w780/lmV8WTvcDHEuvC99WmzkcUETwfv.jpg",
            "https://image.tmdb.org/t/p/w780/7Zm7epVFEovMEVLpM6FvrjhaNXn.jpg",
            "https://image.tmdb.org/t/p/w780/37xIBXgqecMzD5BUd29ogRpaf51.jpg",
            "https://image.tmdb.org/t/p/w780/fPBilOJmi2VAwjgdvfvuLTNjdP8.jpg",
            "https://image.tmdb.org/t/p/w780/uq2dlPtINOcJQ2tdddzuVNEm9NC.jpg",
            "https://image.tmdb.org/t/p/w780/5ipJgXcagDQXHt3KK2Mod7ho0nS.jpg",
        )

        val titleTv = arrayOf(
            "The Mandalorian", "Young Sheldon", "Star Trek: Discovery", "Pokémon",
            "Keeping Up with the Kardashians", "The Graham Norton Show", "Superstore", "Warrior",
            "Emmerdale", "EastEnders","Dahoam is Dahoam","Coronation Street","Gold Rush",
            "Paranormal","Money for Nothing","Days of Our Lives","Shark Tank","Carmel: Who Killed Maria Marta?","The Right Stuff",
            "Génial!"
        )

        val description = arrayOf(
            """After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.
            """.trimIndent(),
            """What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.
            """.trimIndent(),
            """A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.
            """.trimIndent(),
            """Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.
            """.trimIndent(),
            """In a Kentucky orphanage in the 1950s, a young girl discovers an astonishing talent for chess while struggling with addiction.
            """.trimIndent(),
            """A heroic group of teens sheltered from the dangers of the post-apocalyptic world receive a message that inspires them to leave the safety of the only home they have ever known and embark on a cross-country journey to find the one man who can possibly save the world.
            """.trimIndent(),
            """A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?
            """.trimIndent(),
            """This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.
            """.trimIndent(),
            """In 2008, a fight over land in a seaside town near Rome spirals into a deadly battle between organized crime, corrupt politicians and the Vatican.
            """.trimIndent(),
            """Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.
            """.trimIndent(),
            """Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.
            """.trimIndent(),
            """When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way. 
            """.trimIndent(),
            """The story of two vampire brothers obsessed with the same girl, who bears a striking resemblance to the beautiful but ruthless vampire they knew and loved in 1864.
            """.trimIndent(),
            """100 years in the future, when the Earth has been abandoned due to radioactivity, the last surviving humans live on an ark orbiting the planet — but the ark won't last forever. So the repressive regime picks 100 expendable juvenile delinquents to send down to Earth to see if the planet is still habitable.
            """.trimIndent(),
            """Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.
            """.trimIndent(),
            """Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.
            """.trimIndent(),
            """A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.
            """.trimIndent(),
            """Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.
            """.trimIndent(),
            """Each hour-long film follows a different women as they experience “moments that are emotionally raw, thought-provoking and utterly personal
            """.trimIndent(),
            """After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash
            """.trimIndent()
        )

        val popularityTv = arrayOf(
            1862.187,
            131.733,
            155.348,
            82.317,
            84.115,
            69.754,
            45.208,
            39.132,
            32.484,
            26.522,
            24.216,
            39.886,
            26.913,
            27.108,
            26.305,
            26.117,
            34.147,
            24.039,
            20.369,
            22.243,
        )

        val idTv = arrayOf(
            82856,
            71728,
            67198,
            60572,
            14814,
            1220,
            62649,
            73544,
            2527,
            1871,
            40879,
            291,
            34634,
            106590,
            83997,
            881,
            30703,
            111495,
            93786,
            73870,
        )


        for (position in titleTv.indices){
            val itemTV = UpcomingTvEntity(
                name = titleTv[position],
                popularity = popularityTv[position],
                id = idTv[position],
                posterPath = poster[position]
            )
            tvResponse.add(itemTV)
        }
        return tvResponse
    }

    fun generateRemoteUpcominglDummyTv():List<ResultsItemUpTv>{
        val tvResponse = ArrayList<ResultsItemUpTv>()
        val poster = arrayOf(
            "https://image.tmdb.org/t/p/w780/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
            "https://image.tmdb.org/t/p/w780/aESxB2HblKlDzma39xVefa20pbW.jpg",
            "https://image.tmdb.org/t/p/w780/98RYSYsRNKWgrAAFBn0WfploUG7.jpg",
            "https://image.tmdb.org/t/p/w780/rOuGm07PxBhEsK9TaGPRQVJQm1X.jpg",
            "https://image.tmdb.org/t/p/w780/l7QHRrX1EYgQAzNJdCdoEQHoHJ.jpg",
            "https://image.tmdb.org/t/p/w780/vrbqaBXB8AALynQzpWz6JdCPEJS.jpg",
            "https://image.tmdb.org/t/p/w780/maBJkaBM4UqAttn9UkLCfZEVEfk.jpg",
            "https://image.tmdb.org/t/p/w780/eLPxEM7WAvljjFv4UwJ1awhANa9.jpg",
            "https://image.tmdb.org/t/p/w780/ualtuRRNYa9Fj61wSuBVcuvaVDP.jpg",
            "https://image.tmdb.org/t/p/w780/z4jgyI5TpoRZiJTNchkVkMrGQyz.jpg",
            "https://image.tmdb.org/t/p/w780/tGXZEUgSbgufoOpXMImSKPDd5A3.jpg",
            "https://image.tmdb.org/t/p/w780/xe6y8SJU0NyGEECu2LV9cXoY81g.jpg",
            "https://image.tmdb.org/t/p/w780/t7sj18MvyNEKcPEssw3LngX6Ewl.jpg",
            "https://image.tmdb.org/t/p/w780/fCEc46PoQ5uZlX4917jEYY3Ohoh.jpg",
            "https://image.tmdb.org/t/p/w780/lmV8WTvcDHEuvC99WmzkcUETwfv.jpg",
            "https://image.tmdb.org/t/p/w780/7Zm7epVFEovMEVLpM6FvrjhaNXn.jpg",
            "https://image.tmdb.org/t/p/w780/37xIBXgqecMzD5BUd29ogRpaf51.jpg",
            "https://image.tmdb.org/t/p/w780/fPBilOJmi2VAwjgdvfvuLTNjdP8.jpg",
            "https://image.tmdb.org/t/p/w780/uq2dlPtINOcJQ2tdddzuVNEm9NC.jpg",
            "https://image.tmdb.org/t/p/w780/5ipJgXcagDQXHt3KK2Mod7ho0nS.jpg",
        )

        val titleTv = arrayOf(
            "The Mandalorian", "Young Sheldon", "Star Trek: Discovery", "Pokémon",
            "Keeping Up with the Kardashians", "The Graham Norton Show", "Superstore", "Warrior",
            "Emmerdale", "EastEnders","Dahoam is Dahoam","Coronation Street","Gold Rush",
            "Paranormal","Money for Nothing","Days of Our Lives","Shark Tank","Carmel: Who Killed Maria Marta?","The Right Stuff",
            "Génial!"
        )

        val description = arrayOf(
            """After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.
            """.trimIndent(),
            """What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.
            """.trimIndent(),
            """A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.
            """.trimIndent(),
            """Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.
            """.trimIndent(),
            """In a Kentucky orphanage in the 1950s, a young girl discovers an astonishing talent for chess while struggling with addiction.
            """.trimIndent(),
            """A heroic group of teens sheltered from the dangers of the post-apocalyptic world receive a message that inspires them to leave the safety of the only home they have ever known and embark on a cross-country journey to find the one man who can possibly save the world.
            """.trimIndent(),
            """A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?
            """.trimIndent(),
            """This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.
            """.trimIndent(),
            """In 2008, a fight over land in a seaside town near Rome spirals into a deadly battle between organized crime, corrupt politicians and the Vatican.
            """.trimIndent(),
            """Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.
            """.trimIndent(),
            """Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.
            """.trimIndent(),
            """When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way. 
            """.trimIndent(),
            """The story of two vampire brothers obsessed with the same girl, who bears a striking resemblance to the beautiful but ruthless vampire they knew and loved in 1864.
            """.trimIndent(),
            """100 years in the future, when the Earth has been abandoned due to radioactivity, the last surviving humans live on an ark orbiting the planet — but the ark won't last forever. So the repressive regime picks 100 expendable juvenile delinquents to send down to Earth to see if the planet is still habitable.
            """.trimIndent(),
            """Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.
            """.trimIndent(),
            """Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.
            """.trimIndent(),
            """A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.
            """.trimIndent(),
            """Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.
            """.trimIndent(),
            """Each hour-long film follows a different women as they experience “moments that are emotionally raw, thought-provoking and utterly personal
            """.trimIndent(),
            """After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash
            """.trimIndent()
        )

        val popularityTv = arrayOf(
            1862.187,
            131.733,
            155.348,
            82.317,
            84.115,
            69.754,
            45.208,
            39.132,
            32.484,
            26.522,
            24.216,
            39.886,
            26.913,
            27.108,
            26.305,
            26.117,
            34.147,
            24.039,
            20.369,
            22.243,
        )

        val idTv = arrayOf(
            82856,
            71728,
            67198,
            60572,
            14814,
            1220,
            62649,
            73544,
            2527,
            1871,
            40879,
            291,
            34634,
            106590,
            83997,
            881,
            30703,
            111495,
            93786,
            73870,
        )


        for (position in titleTv.indices){
            val itemTV = ResultsItemUpTv(
                name = titleTv[position],
                popularity = popularityTv[position],
                id = idTv[position],
                posterPath = poster[position]
            )
            tvResponse.add(itemTV)
        }
        return tvResponse
    }
}