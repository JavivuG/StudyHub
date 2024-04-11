const svg = document.getElementById("logo");
setInterval(myFunction, 1500);

function myFunction() {
    const colors = [
        "#3ecd5e",
        "#ffa483",
        "#d5ffa2",
        "#ffa3da",
        "#9c9bff",
        "#95ecff",
        "#bf9aca",
        "#bafff4"
    ];
    const rando = () => colors[Math.floor(Math.random() * colors.length)];
    document.documentElement.style.cssText = `
--Birrete-color: ${rando()};
`;
}
