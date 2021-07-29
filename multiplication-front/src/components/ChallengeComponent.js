import * as React from "react";
import ApiClient from "../services/ApiClient";

class ChallengeComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            number1: '',
            number2: '',
            user: '',
            message: '',
            guess: 0,
        };
        this.handleSubmitResult = this.handleSubmitResult.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount(): void {
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
                this.clearInputFields();
                this.componentDidMount();
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
            user: '',
            guess: 0
        });
    }

    render(): React.ReactElement<any> | string | number | {} | React.ReactNodeArray | React.ReactPortal | boolean | null | undefined {
        return (
            <div>
                <div>
                    <h3>Your new Challenge is </h3>
                    <h1> {this.state.number1} x {this.state.number2} </h1>
                </div>
                <form onSubmit={this.handleSubmitResult}>

                    <label>
                        Your Alias :
                        <input type="text" name="user" onChange={this.handleChange} value={this.state.user}/>
                    </label>
                    <br/>
                    <label>
                        Your Guess :
                        <input type="number" min="0" name="guess" value={this.state.guess}
                               onChange={this.handleChange}/>
                    </label>
                    <br/>
                    <input type="submit" value="submit"/>
                </form>
                <h4>{this.state.message}</h4>
            </div>
        );
    }

}

export default ChallengeComponent;