import * as React from 'react'
import GameApiClient from "../services/GameApiClient";
import ApiClient from "../services/ApiClient";
import '../bootstrap.css'
import '../App.css'

class LeaderBoardComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            leaderBoard: [],
            serverError: false
        }
    }

    componentDidMount(): void {
        this.refreshLeaderBoard();
        setInterval(this.refreshLeaderBoard.bind(this), 5000);
    }

    getLeaderBoardData(): Promise {
        return GameApiClient.leaderBoards()
            .then(
                leaderBoard => {
                    if (leaderBoard.ok) {
                        console.log("leaderBoard ===> "+JSON.stringify(leaderBoard));
                        return leaderBoard.json;
                    } else {
                        return Promise.reject("Gamification: error response");
                    }
                }
            );
    }

    getUserAliasData(userIds: number[]): Promise {
        return ApiClient.getUsers(userIds)
            .then(
                users => {
                    if (users.ok) {
                        return users.json;
                    } else {
                        return Promise.reject("Multiplication: error response");
                    }
                }
            );
    }

    updateLeaderBoard(lb) {
        this.setState({
            leaderBoard: lb,
            serverError: false
        });
    }

    refreshLeaderBoard() {
        this.getLeaderBoardData().then(
            lbData => {
                let userIds = lbData.map(row => row.userId);
                if(userIds.length > 0) {
                    this.getUserAliasData(userIds).then(data => {
                        // build a map of id -> alias
                        let userMap = new Map();
                        data.forEach(idAlias => {
                            userMap.set(idAlias.id, idAlias.alias);
                        });
                        // add a property to existing lb data
                        lbData.forEach(row =>
                            row['alias'] = userMap.get(row.userId)
                        );
                        this.updateLeaderBoard(lbData);
                    }).catch(reason => {
                        console.log('Error mapping user ids', reason);
                        this.updateLeaderBoard(lbData);
                    });
                }
             }).catch(reason => {
            console.log("Gamification Server Error ", reason);
            this.setState({serverError: false})
        });
    }

    render(): React.ReactElement<any> | string | number | {} | React.ReactNodeArray | React.ReactPortal | boolean | null | undefined {
        if (this.state.serverError) {
            return (
                <div> We're Sorry , but we can't display game statistics at this moment</div>
            )
        }
        return (
            <div>
                <h3>LeaderBoards</h3>
                <table className="table  table-hover">
                    <thead className="table-light">
                    <tr>
                        <th>User</th>
                        <th>Score</th>
                        <th>Badges</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.state.leaderBoard.map(row =>
                        <tr key={row.userId}>
                            <td>{row.alias ? row.alias : row.userId}</td>
                            <td>{row.totalScore}</td>
                            <td>{row.badges.map(b=><span className="badge" key={b}>{b}</span>)}</td>
                        </tr>
                    )}
                    </tbody>
                </table>
            </div>
        );
    }

}
export default LeaderBoardComponent