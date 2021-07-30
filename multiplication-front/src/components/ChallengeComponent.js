import * as React from "react";
import ApiClient from "../services/ApiClient";
import '../bootstrap.css'
import '../App.css'
import LastAttemptsComponent from "./LastAttemptsComponent";

class ChallengeComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            number1: '',
            number2: '',
            user: '',
            message: '',
            guess: 0,
            lastAttempts: [],
        };
        this.handleSubmitResult = this.handleSubmitResult.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount(): void {
        this.refreshChallenge();
    }

    refreshChallenge() {
        ApiClient.challenge().then(
            res => {
                if (res.ok) {
                    res.json().then(json => {
                        this.setState({
                            number1: json.factorA,
                            number2: json.factorB
                        });
                    });
                } else {
                    this.updateMessage("Can't reach the server");
                }
            }
        );
    }

    handleChange(event) {
        const name = event.target.name;
        this.setState({
            [name]: event.target.value
        });
    }

    handleSubmitResult(event) {
        event.preventDefault();
        ApiClient.sendGuess(
            this.state.user,
            this.state.number1,
            this.state.number2,
            this.state.guess
        ).then(res => {
            if (res.ok) {
                res.json().then(json => {
                    if (json.correct) {
                        this.updateMessage("Congratulations! Your guess is correct");
                    } else {
                        this.updateMessage("Oops! Your guess " + json.resultAttempt +
                            " is wrong, but keep playing!");
                    }
                });
                this.updateLastAttempts(this.state.user);
                this.refreshChallenge();
            } else {
                this.updateMessage("Error: server error or not available");
            }
        });
    }

    updateMessage(message: string) {
        this.setState({
            message: message
        });
    }

    clearInputFields() {
        this.setState({
           guess: 0
        });
    }

    updateLastAttempts(userAlias: string) {
        ApiClient.getAttempt(userAlias)
            .then(res => {
                if (res.ok) {
                    let attempts = [];
                    res.json().then(data => {
                        console.log("getStats for User "+userAlias+"with body "+JSON.stringify(data));
                        let attemptList = data.attempts;
                        attemptList.forEach(item => {
                            attempts.push(item);
                        });
                    });
                    this.setState({
                        lastAttempts: attempts
                    })
                }
            });
    }

    render(): React.ReactElement<any> | string | number | {} | React.ReactNodeArray | React.ReactPortal | boolean | null | undefined {
        return (
            <div>
                <div>
                    <h3>Your new Challenge is </h3>
                    <h1> {this.state.number1} x {this.state.number2} </h1>
                </div>
                <div className="container">
                    <div className="row">
                        <div className="col">
                            <form onSubmit={this.handleSubmitResult} className="form-control">

                                <label className="form-control">
                                    Your Alias :
                                    <input type="text" name="user" onChange={this.handleChange} value={this.state.user}/>
                                </label>
                                <br/>
                                <label className="form-control">
                                    Your Guess :
                                    <input type="number" min="0" name="guess" value={this.state.guess}
                                           onChange={this.handleChange}/>
                                </label>
                                <br/>
                                <input type="submit" value="submit" className="btn btn-primary"/>
                            </form>
                        </div>
                        <h4>{this.state.message}</h4>
                    </div>
                </div>

                <div className="container">
                    <div className="row">
                        <div className="col">
                            {
                                this.state.lastAttempts.length > 0 &&
                                <LastAttemptsComponent lastAttempts={this.state.lastAttempts}/>
                            }
                        </div>
                    </div>
                </div>

            </div>
        );
    }

}

export default ChallengeComponent;