package com.nrup.ktor.routes

import com.nrup.ktor.data.model.Customer
import com.nrup.ktor.data.model.customerStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.customerRouting() {

    route("/customer") {

        // Get Customer List : http://127.0.0.1:8080/customer
        get {
            if (customerStorage.isNotEmpty()) {
                call.respond(customerStorage)
            } else {
                call.respondText("No customer found", status = HttpStatusCode.OK) // 200
            }
        }

        // Get Customer by id : http://127.0.0.1:8080/customer/200
        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing customer id",
                status = HttpStatusCode.BadRequest // 400
            )


            val customer = customerStorage.find { it.id == id } ?: return@get call.respondText(
                "No customer exist with id $id",
                status = HttpStatusCode.NotFound // 404
            )
            call.respond(customer)
        }


        // Add new customer
        post {

            val customer = call.receive<Customer>()
            val id = customerStorage.find { it.id == customer.id }
            if (id != null) {
                call.respondText("Customer already exist", status = HttpStatusCode.OK) // 200
            } else {
                customerStorage.add(customer)
                call.respondText("Customer added successfully", status = HttpStatusCode.Created) // 201
            }

        }

        // Delete customer
        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (customerStorage.removeIf { it.id == id }) {
                call.respondText("Customer deleted successfully", status = HttpStatusCode.Accepted) // 202
            } else {
                call.respondText("No customer exist with id $id", status = HttpStatusCode.NotFound) // 404
            }
        }
    }
}