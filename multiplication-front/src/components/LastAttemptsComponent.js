import * as React from 'react';
import '../bootstrap.css'

class LastAttemptsComponent extends React.Component {

    render(): React.ReactElement<any> | string | number | {} | React.ReactNodeArray | React.ReactPortal | boolean | null | undefined {
        return (
            <table className="table  table-hover">
                <thead className="table-light">
                <th>Challenge</th>
                <th>Your Guess</th>
                <th>Correct</th>
                </thead>
                <tbody>
                </tbody>
                {this.props.lastAttempts.map(
                    attempt =>
                    <tr key={attempt.id} style={{color: attempt.correct ? 'green' : 'red'}}>
                    <td>{attempt.factorA} X {attempt.factorB}</td>
                    <td>{attempt.resultAttempt}</td>
                    <td>{attempt.correct? 'Correct':("InCorrect ( "+attempt.factorA * attempt.factorB +")")}</td>
                    </tr>
                    )}
            </table>
        );
    }
}

export default LastAttemptsComponent;