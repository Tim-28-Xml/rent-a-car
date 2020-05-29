import React from 'react';
import { Row, Col, Tabs, Container, Tab } from "react-bootstrap";
import '../css/Codebook.css'
import CodebookInfo from './CodebookInfo';


function Codebook() {
    return (
      <Container fluid>
          <Row>
                <Col></Col>
                <Col xs={8}>
                    <Tabs defaultActiveKey="1" variant="pills">
                        <Tab eventKey="1" title="View">
                            <CodebookInfo/>
                        </Tab>
                        <Tab eventKey="2" title="Add"></Tab>
                    </Tabs >
                </Col>
                <Col></Col>
          </Row>
      </Container>
    );
  }
  
export default Codebook;