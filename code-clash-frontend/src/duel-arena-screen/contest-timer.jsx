import { useEffect, useState } from "react";

export function Timer( { hh, mm, ss } ) {
    const [time, setTime] = useState(hh*3600+mm*60+ss);

    useEffect( () => {
        if (time <= 0) return;
        const timer = setInterval(() => {
            setTime((prev) => prev-1);
        }, 1000);

        return () => clearInterval(timer);
    },[time])

    return (
        <>
        {String(Math.floor(time / 3600)).padStart(2,"0")}:
        {String(Math.floor(time % 3600 / 60)).padStart(2, "0")}:
        {String(Math.floor(time % 3600 % 60)).padStart(2, "0")}</>
    );
}