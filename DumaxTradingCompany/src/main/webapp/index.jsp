<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Dumax Paints - Transform Your Space with Color</title>
    <link rel="stylesheet" href="styles.css" />

    <style>
      html {
        scroll-behavior: smooth;
      }
    </style>
  </head>

  <body>
    <header id="header">
      <nav>
        <div class="logo">Dumax Paints</div>
        <ul class="nav-links" id="navLinks">
          <li><a href="About.jsp">About us</a></li>
          <li><a href="index.jsp">Home</a></li>
          <li><a href="Paint Color.jsp">Colors</a></li>
          <li><a href="Services.jsp">Services</a></li>
          <li><a href="Order.jsp">My Orders</a></li>
          <li><a href="CheckSession">Visualizer</a></li>
          <li><a href="index.jsp#contact">Contact</a></li>

          <li>
            <%
            if(session.getAttribute("userName") == null){
        %>
            <button class="cta-button">
                <a href="login page.html">Login</a>
            </button>
        <%
            } else {
        %>
            <button class="cta-button">
                <a href="logout"> Logout</a>
            </button>
        <%
            }
        %>
          </li>
        </ul>

        <div class="menu-toggle" id="menuToggle">
          <span></span>
          <span></span>
          <span></span>
        </div>
      </nav>
    </header>

    <section class="hero" id="home">
      <div class="hero-content">
        <h1>Transform Your Space with Color</h1>
        <p>
          Discover premium quality paints, expert color consultation, and
          professional painting services for homes and businesses.
        </p>
        <div class="hero-buttons">
          <a href="CheckSession#vc">
            <button class="btn-primary" onclick="scrollToVisualizer()">
              Try Color Visualizer
            </button>
          </a>
          <a href="Paint Color.jsp#acc">
            <button class="btn-secondary" onclick="scrollToColors()">
              Explore Colors
            </button>
          </a>
        </div>
      </div>
    </section>

    <!-- Footer Section (Now Contact Target) -->
    <footer id="contact">
      <div class="footer-content">
        <div class="footer-section">
          <h4>About Dumax Paints</h4>
          <p style="color: rgba(255, 255, 255, 0.7); line-height: 1.8">
            India's trusted paint brand delivering premium quality paints,
            expert services, and innovative color solutions for over 15 years.
          </p>
        </div>

        <div class="footer-section">
          <h4>Our Products</h4>
          <ul>
            <li><a href="Services.jsp#pp">Interior Paints</a></li>
            <li><a href="Services.jsp#pp">Exterior Paints</a></li>
            <li><a href="Services.jsp#pp">Wood Finishes</a></li>
            <li><a href="Services.jsp#dp">Designer Textures</a></li>
          </ul>
        </div>

        <div class="footer-section">
          <h4>Our Services</h4>
          <ul>
            <li><a href="Services.jsp#services">Home Painting</a></li>
            <li><a href="Services.jsp#cc">Color Consultation</a></li>
            <li>
              <a href="Services.jsp#services">Exterior Shield Painting</a>
            </li>
            <li><a href="Services.jsp#services">Waterproofing</a></li>
          </ul>
        </div>

        <div class="footer-section">
          <h4>Resources</h4>
          <ul>
            <li><a href="CheckSession#vc">AI Visualizer</a></li>
            <li><a href="About.jsp#help">Find a Store</a></li>
            <li><a href="About.jsp#faq">FAQs</a></li>
            <li><a href="About.jsp#help">Contact Support</a></li>
          </ul>
        </div>
      </div>

      <div class="footer-bottom">
        <p>
          &copy; 2025 Dumax Paints. All rights reserved. | Transforming Spaces,
          Creating Happiness
        </p>
      </div>
    </footer>

    <script src="script.js"></script>
  </body>
</html>
