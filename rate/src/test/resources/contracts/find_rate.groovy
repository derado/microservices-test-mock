import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return matching rate"

    request {
        url "/rate"
        method GET()
    }

    response {
        status 200
        headers {
            contentType applicationJson()
        }
        body (
                uuid: $(regex('(.*)')),
                rate: 10
        )
    }
}