import { NavBar } from "../Components/nav-bar"
import { AboutMe } from "./about-me"
import { AboutCodeClash } from "./about-setion"
import { DescriptionBar } from "./description-setion"
import { DuelBar } from "./duel-section"
import './home-page.css'

export function HomePage() {
    return (
        <>
            <div>
                <NavBar />
            </div>
            <div className="duel-container">
                <DescriptionBar />
                <DuelBar />
            </div>
            <div>
                <AboutCodeClash />
            </div>
            <div>
                <AboutMe />
            </div>
        </>
    )
}