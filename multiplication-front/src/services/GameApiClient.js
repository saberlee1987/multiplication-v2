class GameApiClient {

    static SERVER_URL = "http://localhost:8002";
    static GET_LEADER_BOARD ="/gamification/leaders";

    static leaderBoards(): Promise<Response>{
        return fetch(GameApiClient.SERVER_URL+GameApiClient.GET_LEADER_BOARD);
    }

}
export default GameApiClient