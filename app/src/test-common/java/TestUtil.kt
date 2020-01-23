import com.sample.githubsample.vo.Repo

object TestUtil {

    fun createRepos(count: Int, author: String, name: String, description: String): List<Repo> {
        return (0 until count).map {
            createRepo(
                author = author + it,
                name = name + it,
                description = description + it
            )
        }
    }

    fun createRepo(author: String, name: String, description: String) = createRepo(
        id = Repo.UNKNOWN_ID,
        name = name,
        author = author,
        description = description
    )


    fun createRepo(id: Int, author: String, name: String, description: String) = Repo(
        id = id,
        name = name,
        description = description,
        author = author,
        avatar = "https://github.com/google-research.png",
        stars = 1,
        fork = 2,
        currentPeriodStars = 3,
        language = "GO",
        languageColor = "#3572A5",
        url = "https://github.com/google-research/flax"
    )
}