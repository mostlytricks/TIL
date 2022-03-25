import React, { useEffect, useState } from "react";
import styled from "styled-components";

import { BiToggleLeft, BiToggleRight, BiMenu, BiX } from "react-icons/bi";

const HeaderStyled = styled.div``;

const Header = () => {
  console.log("newly here");

  useEffect(() => {
    const menuToggleButton = document.querySelector(".menu-toggle-button");
    const menuElement = document.querySelector(".menu");

    const toggleMenu = () => {
      menuElement.classList.toggle("active");
      menuToggleButton.classList.toggle("active");
    };

    const listLinkMenu = document.querySelectorAll(".menu > .list .list-link");
    listLinkMenu.forEach((x) => {
      x.addEventListener("click", () => {
        menuElement.classList.remove("active");
        menuToggleButton.classList.remove("active");
      });
    });
    menuToggleButton.addEventListener("click", toggleMenu);
  
    const bodyElement = document.querySelector("body"); // or, docuemnt.body;
    const themeToggleButton = document.querySelector('.theme-toggle-button');
    const currentTheme = localStorage.getItem('darkTheme');
    
    if(currentTheme){
        bodyElement.classList.add("dark-theme");
    };

    const toggleTheme = () =>{
        bodyElement.classList.toggle('dark-theme');
        if( bodyElement.classList.contains('dark-theme')){
            localStorage.setItem('darkTheme', 'active');
        } else {
            localStorage.removeItem('darkTheme');
        }
    }

    themeToggleButton.addEventListener('click', toggleTheme);
}, []);


  return (
    <div id="home">
      <nav className="navbar container">
        <a href="/portfolio" className="logo">
          Traby.
        </a>
        <div className="navbar-buttons">
          <button
            type="button"
            className="button icon-button menu-toggle-button"
          >
            <i className="bx bx-menu open-icon">
              <BiMenu></BiMenu>
            </i>
            {/* i tag : 다른 형식의 text 보여줄경우 사용, 통상 italic 적용 or icon의 i. */}
            <i className="bx bx-x close-icon">
              <BiX></BiX>
            </i>
            
                    </button>
          <button
            type="button"
            className="button icon-button theme-toggle-button"
          >
            <i className="bx bx-toggle-left theme-off">
              <BiToggleLeft></BiToggleLeft>
            </i>
            <i className="bx bx-toggle-right theme-on">
              <BiToggleRight></BiToggleRight>
            </i>
          </button>
        </div>
        <div className="menu">
          <ul className="list">
            <li className="list-item">
              <a href="#" className="list-link">
                <span>01</span> About me
              </a>
            </li>
            <li className="list-item">
              <a href="#" className="list-link">
                <span>02</span> Portfolio
              </a>
            </li>
            <li className="list-item">
              <a href="#" className="list-link">
                <span>03</span> Contact
              </a>
            </li>
          </ul>
        </div>
      </nav>
    </div>
  );
};
export default Header;

