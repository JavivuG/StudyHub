const svg = document.getElementById('logo');
setInterval(myFunction, 1500);

function myFunction() {
    const colors = [
        'red',
        'orange',
        'yellow',
        'green',
        'blue',
        'purple',
        'pink',
    ];
    const rando = () =>
        colors[Math.floor(Math.random() * colors.length)];
    document.documentElement.style.cssText = `
--Birrete-color: ${rando()};
`;
}