import { NavLink } from 'react-router-dom';

export default function Header() {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
      <div className="container">
        <NavLink className="navbar-brand fw-bold" to="/">Boocinema</NavLink>
        <div className="collapse navbar-collapse">
          <ul className="navbar-nav ms-auto">
            <li className="nav-item"><NavLink className="nav-link" to="/">Home</NavLink></li>
            <li className="nav-item"><NavLink className="nav-link" to="/films">Films</NavLink></li>
            <li className="nav-item"><NavLink className="nav-link" to="/genres">Generi</NavLink></li>
          </ul>
        </div>
      </div>
    </nav>
  );
};