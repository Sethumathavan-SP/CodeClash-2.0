import ProfileImage from '../assets/home-page-assert/profile-image.jfif'
import { Timer } from './contest-timer';

export function ArenaNavBar() {
    return ( 
        <>
            <div>
                <h1>Code Clash</h1>
            </div>
            <div className='nav-links'>
                <button className='run-button'>Run</button>
                <button className='submit-button'>Submit</button>
            </div>
            <div>
                <Timer hh={0} mm={15} ss={0} />
                <button>Quit</button>
            </div>
        </>
    );
}