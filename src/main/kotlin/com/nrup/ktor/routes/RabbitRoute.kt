import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

//private const val BASE_URL = "http://localhost:8080"

// in cmd type "ipconfig" and copy the address of your Ethernet adapter: IPV4 Address
private const val BASE_URL = "http://10.37.54.125:8080"

private val rabbits = listOf(
    Rabbit(name = "Carl", description = "A cute brown rabbit", imageUrl = "$BASE_URL/rabbits/1_rabbit.jpg"),
    Rabbit(name = "Emma", description = "Emma like to eat apple", imageUrl = "$BASE_URL/rabbits/2_rabbit.jpg"),
    Rabbit(name = "Florian", description = "Florian is very shy", imageUrl = "$BASE_URL/rabbits/3_rabbit.jpg"),
    Rabbit(name = "Mike", description = "Mike is very strong", imageUrl = "$BASE_URL/rabbits/4_rabbit.jpg"),
    Rabbit(name = "Giana", description = "Giana is beautiful", imageUrl = "$BASE_URL/rabbits/5_rabbit.jpg")
)

// http://127.0.0.1:8080/randomrabbit
// Extension Function
fun Route.randomRabbitRouting() {
    get("/randomrabbit") {
        call.respond(
            HttpStatusCode.OK,
            rabbits.random()
        )
    }
}

/*
 Response Json
    {
        "name": "Mike",
        "description": "Mike is very strong",
        "imageUrl": "http://10.37.54.125:8080/rabbits/4_rabbit.jpg"
    }

*/