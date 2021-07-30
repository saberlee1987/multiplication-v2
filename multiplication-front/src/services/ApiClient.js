class ApiClient {
    static SERVER_URL = "http://localhost:8001";
    static GET_CHALLENGE = "/challenges/random";
    static POST_RESULT = "/challenges/attempt";
    static GET_ATTEMPTS_BY_ALIAS="/challenges/lastAttempts?alias=";

    static challenge(): Promise<Response> {
        return fetch(ApiClient.SERVER_URL + ApiClient.GET_CHALLENGE);
    }

    static sendGuess(user: string, number1: number, number2: number, guess: number): Promise<Response> {
        return fetch(ApiClient.SERVER_URL + ApiClient.POST_RESULT, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "userAlias": user,
                "factorA": number1,
                "factorB": number2,
                "guess": guess
            })
        });
    }

    static getAttempt(userAlias:string):Promise<Response>{
        console.log("Get Attempts for "+userAlias);
        return  fetch(ApiClient.SERVER_URL+ApiClient.GET_ATTEMPTS_BY_ALIAS+userAlias);
    }
}

export default ApiClient