import { FlowLayer } from "./flowLayer";

export function DescriptionBar() {
    return (
        <div className="duel-description">
            <div className="description-bar-header">
                <h1>FACE OFF IN <span>EPIC 1V1</span> BATTLES</h1>
            </div>
            <div>
                <p>Experience the thrill of intense 1v1 battles in our action-packed game. Challenge your friends or compete against players worldwide in fast-paced duels. Prove your skills and climb the leaderboards to become the ultimate champion!</p>
            </div>
            <div className="description-flow-layers">
                <FlowLayer header="Instant Pairing" desc="Minimize latency, maximize focus. Jump into the arena and start solving the moment your opponent appears" />
                <FlowLayer header="Ranked Ladder" desc="Climb From the Bottom to Where You Really Belong" />
            </div>
        </div>
    );
}