import ProfileImage from '../assets/home-page-assert/profile-image.jfif';

export function NavBar() {
    return (
        <div className="nav-bar">
            <div>
                <h1>Code Clash</h1>
            </div>
            <div className='nav-links'>
                <a href="#">Home</a>
                <a href="#">LeaderBoard</a>
                <a href="#">About</a>
                <a href="#">Profile</a>
            </div>
            <div>
                <img src={ProfileImage}></img>
            </div>
        </div>
    )
}