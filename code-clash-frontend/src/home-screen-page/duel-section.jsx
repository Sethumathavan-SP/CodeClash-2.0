import duelImage from "../assets/home-page-assert/duel-image.jfif";

export function DuelBar() {
    return (
        <div className="duel-bar">
            <img src={duelImage} />
            <button>Start Duel</button>
        </div>
    );
}